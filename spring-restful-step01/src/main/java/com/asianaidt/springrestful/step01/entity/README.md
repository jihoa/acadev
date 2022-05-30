#### JPA를 이용한 모델 객체 생성
    1. 의존성 추가
        // spring web, jpa 관련 라이브러리
        implementation 'org.springframework.boot:spring-boot-starter-web'
        implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
        testImplementation 'com.h2database:h2'
        // 롬복 라이브러리
        compileOnly 'org.projectlombok:lombok'
        annotationProcessor 'org.projectlombok:lombok'
        // 객체 유효성 검증 라이브러리
        implementation 'org.springframework.boot:spring-boot-starter-validation'

    2. DB 연결 확인 
        사전 설치된 경로에서 h2.bat를 실행하여 DB 기동
    2.1 Using JPA
    2.1.1 @Entity
        spring-boot-starter-data-jpa 의존성을 추가하고 @Entity 를 붙이면 테이블과 자바 클래스가 매핑됨.

    2.1.2 @Id
        테이블의 식별자로 설정. primary key
        키 설정 전략 필요 

    2.1.3 @GeneratedValue
        전략은 GenerationType.AUTO 로 해줍니다. 데이터베이스에 넣을때 자동으로 관리해줌.
    
    2.2 Using Lombok
    2.2.1 @Setter @Getter
        setXXX(), getXXX()를 컴파일 시점에 자동 생성

    2.2.2 @NoArgsConstructor
        인자가 없는 생성자를 만들어 줌.
    
    2.2.3 @ToString
        ToString 함수 구현
        
    2.3 Using validation
    2.3.1 @NotBlank
        널 및 공백 체크
        @NotBlank(message = "사용자이름은 필수 입력 사항입니다.")
    2.3.2 @Size
        최소 최대 입력 자리수 체크
        @Size(min = 8, max = 12, message = "비밀번호는 8~12 로 입력해주세요.")
    2.3.3 @Email
        메일 유효성 체크
        @Email을 null을 허용하므로 @NotNull과 함께 사용해야 의도한 대로 동작함.
    
    