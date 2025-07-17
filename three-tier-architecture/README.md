# 3계층 아키텍처

이 모듈은 고전적인 3계층 아키텍처 패턴을 보여줍니다.

## 아키텍처 개요

3계층 아키텍처는 애플리케이션을 세 개의 논리적 계층으로 나눕니다:

1. **프레젠테이션 계층 (Presentation Layer)**
   - HTTP 요청과 응답을 처리
   - 뷰를 렌더링하거나 클라이언트에 데이터 반환
   - 기본적인 입력 유효성 검사 수행
   - 파일: 
   ```
   `com.example.threetier.controller.WebController.java`
   ``` 
   
2. **비즈니스 계층 (Business Layer)**
   - 비즈니스 로직 포함
   - 프레젠테이션 계층과 데이터 계층 사이의 데이터 흐름 조율
   - 비즈니스 유효성 검사 수행
   - 파일: 
   ```
   `com.example.threetier.service.ConferenceService.java`
   ```
   
3. **데이터 계층 (Database Layer)**
   - 데이터베이스와의 상호작용(영속성 및 검색)을 처리
   - 데이터베이스와 상호작용
   - 파일: 
   ```
   `com.example.threetier.dao.ConferenceMapper.java`, 
   `com.example.threetier.entity.ConferenceEntity.java`
   ```

## 데이터 전송

- 프레젠테이션 계층과 비즈니스 계층 간에는 DTO(`com.example.threetier.dto.ConferenceDto.java`, `com.example.threetier.dto.ConferenceResponse.java`)를 사용하여 데이터 전송
- 비즈니스 계층은 데이터 계층의 엔티티(ConferenceEntity)를 직접 사용하여 비즈니스 로직을 처리합니다. 이로 인해 개발이 단순해지는 장점이 있지만, 비즈니스 로직이 데이터 기술에 종속될 수 있습니다.

## 주요 특징

- 명확한 관심사 분리
- 각 계층은 특정 책임을 가짐
- 계층은 잘 정의된 인터페이스를 통해 통신
- 상위 계층은 하위 계층에 의존하지만, 그 반대는 아님
- 엔티티는 서비스 계층에서 직접 사용됨

## 제어 흐름

1. 컨트롤러가 클라이언트로부터 요청을 받음
2. 컨트롤러가 적절한 서비스 메서드 호출
3. 서비스가 비즈니스 로직을 수행하고 DAO(Mapper)를 호출합니다.
4. DAO(Mapper)가 데이터베이스와 상호작용합니다.
5. 서비스가 엔티티를 DTO로 변환
6. 컨트롤러가 DTO를 클라이언트에 반환
