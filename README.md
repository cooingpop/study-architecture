# 아키텍처 스터디 프로젝트 분석

## 프로젝트 개요
이 프로젝트는 소프트웨어 아키텍처 패턴을 학습하기 위한 스터디 프로젝트로, 동일한 기능을 구현하는 두 가지 다른 아키텍처 패턴(3-tier와 4-tier)을 비교할 수 있도록 구성되어 있습니다. 각 모듈은 간단한 컨퍼런스 참가자 등록 시스템을 구현하고 있습니다.

## 3-tier 아키텍처 분석

### 구조 개요
3-tier 아키텍처는 애플리케이션을 세 개의 논리적 계층으로 나눕니다:
1. 프레젠테이션 계층 (웹 계층): 사용자의 요청과 응답을 처리합니다.
2. 비즈니스 계층 (서비스 계층): 핵심 비즈니스 로직을 수행합니다.
3. 데이터 계층 (DAO 계층): 데이터베이스와의 통신을 담당합니다.

### 주요 특징
- 명확한 관심사 분리
- 각 계층은 특정 책임을 가집니다.
- 계층은 잘 정의된 인터페이스를 통해 통신합니다.
- 상위 계층은 하위 계층에 의존하지만, 그 반대는 아닙니다.
- 서비스의 엔티티 직접 사용: 비즈니스 계층(Service)이 데이터 계층의 Entity를 직접 참조하고 사용하여 로직을 처리합니다. 
   이로 인해 구현이 단순하지만, 비즈니스 로직이 데이터 기술에 종속될 수 있습니다.

## 4-tier 아키텍처 분석

### 구조 개요
4-tier 아키텍처는 애플리케이션을 네 개의 논리적 계층으로 나눕니다:

1. 프레젠테이션 계층 (Presentation Layer)
   - 사용자와의 상호작용(HTTP 요청/응답)을 담당합니다.
   - 자체 DTO를 사용하여 데이터를 주고받습니다.

2. 비즈니스 계층 (Business Layer)
   - 핵심 비즈니스 로직을 처리하고 트랜잭션을 관리합니다.
   - 하위의 영속성 계층 인터페이스에만 의존하며, 실제 데이터베이스 기술은 알지 못합니다.

3. 영속성 계층 (Persistence Layer)
   - 데이터 접근에 대한 표준 방법을 인터페이스로 정의합니다.
   - '무엇을' 가져올지, '어떻게' 저장할지에 대한 '기능 명세'의 역할을 합니다.

4. 데이터 소스 계층 (Data Source Layer)
   - 영속성 계층의 인터페이스를 실제 기술로 구현합니다.
   - JPA, MyBatis 등 특정 기술을 사용하여 데이터베이스와 통신하고, Entity 클래스를 직접 다룹니다.


### 주요 특징
- 명확한 관심사 분리
- 각 계층은 특정 책임을 가집니다.
- 계층 간 통신은 잘 정의된 인터페이스를 통해 이루어집니다.
- 프레젠테이션 계층은 인프라스트럭처 계층과 직접 상호작용하지 않습니다.
- 패키지 구조가 계층을 명확하게 반영합니다.

## 두 아키텍처의 주요 차이점

1. **계층 구조**:
   - 3-tier: 프레젠테이션, 비즈니스, 데이터의 3개 계층으로 구성됩니다.
   - 4-tier: 프레젠테이션, 비즈니스, 영속성, 데이터 소스의 4개 계층으로 구성되어, 데이터 관련 계층이 인터페이스와 구현으로 명확히 분리됩니다.

2. **데이터 처리 방식**:
   - 3-tier: 비즈니스 계층이 데이터베이스와 직접 통신하는 Entity를 직접 참조하고 사용합니다.
   - 4-tier: 비즈니스 계층은 데이터베이스 Entity를 모르며, 오직 영속성 계층의 인터페이스에만 의존하여 데이터를 요청하고 처리합니다.

3. **계층 간 의존성**:
   - 3-tier: 비즈니스 계층이 데이터 계층의 구체적인 기술에 의존하는 강한 결합 구조를 가질 수 있습니다.
   - 4-tier: 영속성 계층(인터페이스)을 중간에 두어 비즈니스 계층과 데이터 소스 계층의 의존성을 끊어내는 느슨한 결합 구조를 가집니다.

## 결론

이 프로젝트는 동일한 기능을 구현하는 두 가지 다른 아키텍처 패턴을 비교함으로써, 
각 아키텍처의 장단점과 적용 방식을 학습할 수 있도록 설계되었습니다.
3-tier 아키텍처는 단순하고 직관적인 구조 덕분에 초기 개발 속도가 빠르다는 장점이 있습니다. 
하지만 비즈니스 계층이 데이터 기술에 직접 의존하기 때문에, 시스템이 복잡해질수록 유지보수가 어려워지고 다른 기술로의 전환이 힘들어지는 한계를 가집니다.

반면, 전통적인 4-tier 아키텍처는 **영속성 계층(인터페이스)**이라는 중간 계층을 둠으로써 비즈니스 로직과 데이터 기술을 완전히 분리합니다.
이러한 설계는 초기 구조 설정이 다소 복잡할 수 있지만, 다음과 같은 결정적인 이점을 제공합니다
