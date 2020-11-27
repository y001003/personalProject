package kr.spring.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.spring.member.domain.MemberVO;
import kr.spring.member.service.MemberService;
import kr.spring.util.AuthCheckException;

@Controller
public class MemberController {
	//로그 대상 지정
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	MemberService memberService;
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	
	//회원가입 폼 호출
	@RequestMapping(value="/member/register.do",method=RequestMethod.GET)
	public String form() {
		return "memberRegister";
	}
	//회원가입 처리
	@RequestMapping(value="/member/register.do",method=RequestMethod.POST)
	public String submit(@Valid MemberVO memberVO,BindingResult result) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<MemberVO>> : " + memberVO);
		}
		
		//유효성 체크 결과 에러가 있으면 폼 호출
		if(result.hasErrors()) {
			return form();
		}
		//회원가입
		memberService.insert(memberVO);
		
		return "redirect:/main/main.do";
	}
	//로그인 폼 호출
	@RequestMapping(value="/member/login.do",method=RequestMethod.GET)
	public String formLogin() {
		return "memberLogin";
	}
	//로그인 처리
	@RequestMapping(value="/member/login.do",method=RequestMethod.POST)
	public String submitLogin(@Valid MemberVO memberVO,BindingResult result,HttpSession session) {
		//로그 표시
		if(log.isDebugEnabled()) {
			log.debug("<<Member>> : " + memberVO);
		}
		//유효성체크 id와 비밀번호만 체크
		if(result.hasFieldErrors("id")||result.hasFieldErrors("passwd")) {
			return formLogin();//체크 결과 에러가 있으면 폼을 호출
		}
		//로그인 체크
		try {
			MemberVO member = memberService.selectCheckMember(memberVO.getId());
			
			boolean check = false;
			if(member != null) {
				//비밀번호 체크
				check = member.isCheckedPasswd(memberVO.getPasswd());
			}
			if (check) {
				//로그인 성공
				session.setAttribute("user_id", member.getId());
				session.setAttribute("user_auth", member.getAuth());
				session.setAttribute("user_num", member.getMem_num());
				session.setAttribute("user", member);
				
				if(log.isDebugEnabled()) {
					log.debug("==로그인 성공=");
					log.debug("<<user_id>> : "+member.getId());
					log.debug("<<user_auth>> : "+member.getAuth());
					log.debug("<<user>> : "+member);
				}
				return "redirect:/main/main.do";
			}else {
				//로그인 실패
				throw new AuthCheckException();
			}
			
		}catch (AuthCheckException e) {
			//로그인 실패시 에러코드를 지정하고 폼을 호출함
			result.reject("invalidIdOrPassword");
			if(log.isDebugEnabled()) {
				log.debug("==로그인 실패==");
			}
			return formLogin();
		}
	}
	
	//로그아웃
	@RequestMapping("/member/logout.do")
	public String processLogout(HttpSession session) {
		
		//로그아웃 처리
		session.invalidate();
		
		return "redirect:/main/main.do";
	}
	
	//회원정보
	@RequestMapping("/member/detail.do")
	public String process(HttpSession session,Model model) {
		//세션에 저장되어 있는 mem_num 반환
		int mem_num = (Integer)session.getAttribute("user_num");
		MemberVO member = memberService.selectMember(mem_num);
		
		if(log.isDebugEnabled()) {
			log.debug("<<MemberVO>> : " + member);
		}
		model.addAttribute("member",member);
		
		return "memberView";
	}
	
	//회원정보 수정 폼
	@RequestMapping(value="/member/update.do",method=RequestMethod.GET)
	public String formUpdate(HttpSession session,Model model) {
		//세션에 저장되어 있는 mem_num 반환
		int mem_num = (Integer)session.getAttribute("user_num");
		MemberVO memberVO = memberService.selectMember(mem_num);
		
		model.addAttribute("memberVO",memberVO);
		
		return "memberModify";
	}
	//회원정보 수정처리
	@RequestMapping(value="/member/update.do",method=RequestMethod.POST)
	public String submitUpdate(@Valid MemberVO memberVO,BindingResult result) {
		//로그 표시
		if(log.isDebugEnabled()) {
			log.debug("<<MemberVO>> : " + memberVO);
		}
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasFieldErrors("name")||
		   result.hasFieldErrors("phone")||
		   result.hasFieldErrors("email")||
		   result.hasFieldErrors("zipcode")||
		   result.hasFieldErrors("address1")||
		   result.hasFieldErrors("address2")) {
			return "memberModify";
		}
		
		//회원정보 수정
		memberService.update(memberVO);
		
		return "redirect:/member/detail.do";
	}
	//비밀번호 변경 폼
	@RequestMapping(value="/member/changePassword.do",method=RequestMethod.GET)
	public String formChangePassword(HttpSession session,Model model) {
		
		int mem_num = (Integer) session.getAttribute("user_num");
		
		MemberVO memberVO = memberService.selectMember(mem_num);
		
		model.addAttribute("memberVO",memberVO);
		
		return "memberChangePassword";
	}
	//비밀번호 변경처리
	@RequestMapping(value="/member/changePassword.do",method=RequestMethod.POST)
	public String submitChangePassword(@Valid MemberVO memberVO,BindingResult result) {
		//로그 표시
		if(log.isDebugEnabled()) {
			log.debug("<<MemberVO>> : " + memberVO);
		}
		
		//유효성 체크
		if(result.hasFieldErrors("passwd")) {
			
			return "memberChangePassword";
		}
		//비밀번호 인증
		MemberVO member = memberService.selectMember(memberVO.getMem_num());
			//db에 저장되어 있는 비빌번호			  받아온 Old_passwd 값
		if(!member.getPasswd().equals(memberVO.getOld_passwd())) {
								//필드			에러코드
			result.rejectValue("old_passwd", "invalidPassword");
			return "memberChangePassword";
		};
		
		//비밀번호 변경
		memberService.updatePassword(memberVO);
		
		return "redirect:/member/detail.do";
	}
	
	//회원탈퇴폼
	@RequestMapping(value="/member/delete.do",method=RequestMethod.GET)
	public String formDelete(HttpSession session,Model model) {
		
		int mem_num = (Integer) session.getAttribute("user_num");
		MemberVO memberVO = new MemberVO();
		memberVO.setMem_num(mem_num);
		
		model.addAttribute("memberVO",memberVO);
		
		return "memberDelete";
	}
	
	@RequestMapping(value="/member/delete.do",method=RequestMethod.POST)
	public String submitDelete(@Valid MemberVO memberVO,BindingResult result,HttpSession session) {
		//로그표시
		if(log.isDebugEnabled()) {
			log.debug("<<MemberVO>> : 삭제 시도!"+memberVO);
		}
		//비밀번호만 유효성 체크, 유효셩 체크 결과 에러가 있으면 폼 호출
		if(result.hasFieldErrors("passwd")) {
			return "memberDelete";
		}
		//비밀번호 인증
		try {
			MemberVO member = memberService.selectMember(memberVO.getMem_num());
			boolean check = false;
			
			if(member!=null) {
				//비밀번호 인증
				check = member.isCheckedPasswd(memberVO.getPasswd());
			}
			if(check) {
				//비밀번호 인증 성공
				//회원 탈퇴
				memberService.delete(memberVO.getMem_num());
				//로그아웃
				session.invalidate();
				return "redirect:/main/main.do";
			}else {
				//비밀번호 인증 실패
				throw new AuthCheckException();
			}
		} catch (AuthCheckException e) {
			//비밀번호 인증 실패시 에러 코드 지정하고 폼 호출
			result.rejectValue("passwd", "invalidPassword");
			return "memberDelete";
		}
	}
}







