# 4계층 아키텍처

이 모듈은 현대적인 4계층 아키텍처 패턴을 보여줍니다.

## 아키텍처 개요

4계층 아키텍처는 애플리케이션을 네 개의 논리적 계층으로 나눕니다:

1. **프레젠테이션 계층 (Presentation Layer)**
   - HTTP 요청과 응답을 처리
   - 뷰를 렌더링하거나 클라이언트에 데이터 반환
   - 기본적인 입력 유효성 검사 수행
   - 입출력에 자체 DTO 사용
   - 파일: 
   ```
   `com.example.fourtier.controller.WebController.java`, 
   `com.example.fourtier.controller.dto.ConferenceResponse.java`, 
   `com.example.fourtier.controller.dto.ConferenceRequest.java`
   ``` 

2. **비즈니스 계층 (Business Layer)**
   - 유스케이스의 흐름을 조율
   - 비즈니스 로직 수행
   - 트랜잭션 관리
   - 엔티티와 DTO 간 변환
   - 파일: 
   ```
   `com.example.fourtier.application.conference.service.ConferenceService.java`, 
   `com.example.fourtier.application.conference.dto.ConferenceDto.java`
   ``` 

3. **영속성 계층 (Persistence Layer)**
   - 데이터 접근 로직의 '무엇을' 할지(추상화)를 정의합니다.
   - 데이터 접근 인터페이스를 통해 데이터 조작 방법을 선언합니다.
   - 실제 구현 방법은 정의하지 않고 필요한 기능만 명시합니다.
   - 파일: 
   ```
   `com.example.fourtier.infrastructure.persistence.ConferenceMapper.java`
   ```

4. **데이터베이스 계층 (Database Layer)**
   - 다른 계층을 지원하는 기술적 기능 제공
   - 영속성 메커니즘 구현 (MyBatis를 통한 SQL 매핑)
   - 데이터베이스 엔티티 정의
   - 외부 시스템과의 통합
   - 파일: 
   ```
   `com.example.fourtier.infrastructure.persistence.entity.ConferenceEntity.java`, 
   `src/main/resources/mappers/ConferenceMapper.xml`
   ``` 

## 데이터 전송

- 프레젠테이션 계층과 애플리케이션 계층 간에는 DTO를 사용하여 데이터 전송
- 비즈니스 계층은 영속성 계층의 인터페이스를 통해 데이터를 주고 받는다.
- 애플리케이션 계층은 엔티티를 DTO로 변환하여 프레젠테이션 계층에 전달

## 주요 특징

- 명확한 역할 분리: 각 계층은 하나의 책임에만 집중합니다. (예: Service는 DB 기술을 모름)
- 각 계층은 특정 책임을 가짐
- 계층 간 통신은 잘 정의된 인터페이스를 통해 이루어짐
- 프레젠테이션 계층은 인프라스트럭처 계층과 직접 상호작용 하지 않음
- 의존성 규칙: 의존성 흐름은 항상 프레젠테이션 → 비즈니스 → 영속성 → 데이터 소스 순으로 흐릅니다.

## 제어 흐름

1. 컨트롤러가 클라이언트로부터 요청을 받습니다.
2. 컨트롤러는 받은 요청을 처리하기 위해 적절한 서비스를 호출합니다.
3. 서비스는 비즈니스 규칙에 따라 로직을 처리한 후, 데이터 저장을 위해 리포지토리를 호출합니다.
4. 리포지토리의 구현체는 매퍼와 엔티티를 이용해 실제 데이터베이스와 통신합니다.
5. 서비스는 처리 결과를 클라이언트에 전달할 **응답 DTO(Response DTO)**로 변환합니다.
6. 컨트롤러가 최종 변환된 응답 DTO를 클라이언트에 반환하며 작업이 마무리됩니다.
