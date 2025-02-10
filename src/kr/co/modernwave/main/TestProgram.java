package kr.co.modernwave.main;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

public class TestProgram {

	@Test
	public void saveMemberTest() {
		// given : 멤버를 저장하기 위한 준비 과정
		final String name = "b1uesoda";
		final int age = 25;

		final Member member = new Member(name, age);
		final MemberService memberService = new MemberService();
		// when : 실제로 멤버를 저장
		final String savedId = memberService.save(member);

		// then : 멤버가 잘 추가되었는지 확인
		final Member savedMember = memberService.findById(savedId);
		assertEquals(name, savedMember.getName());
		assertEquals(age, savedMember.getAge());
	}
}

class Member {

	private String name;
	private int age;
	private String uuid;

	public Member(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
}

class MemberService {

	private List<Member> list = new ArrayList<>();

	public MemberService() {
	}

	public Member findById(String uuid) {
		// list.stream() : 리스트에서 스트림 생성
		// filter() : 조건에 맞는 요소 필터링
		// findFirst() : 조건에 맞는 첫 번째 요소 반환
		// orElse(null) : 요소가 없는 경우 null 반환
		return list.stream().filter(member -> uuid.equals(member.getUuid())).findFirst().orElse(null);

	}

	public String save(Member member) {
		String uuid = makeUuid(member);
		list.add(member);
		
		return uuid;
	}

	private String makeUuid(Member member) {
		String uuid = UUID.randomUUID().toString();
		member.setUuid(uuid);
		return uuid;
	}
}