# &#128154; *23#-AOS

## **Git Branch Convention**

1. branch 이름은 snake_case 표기법 이용
2. "Android-initial"로 형식 통일    
 ex)Android-KSH   
3. default branch는 main
   
-> main에서 본인 브랜치 생성  
-> add-commit-pull-push-merge  
-> 작업시작할때 pull받고 시작하는거 잊지않기


## **Git Commit Message Convention**

### **Issue Number**

- 솝커톤 내에서 사용하지 않기로 함
- 
### **Base Structure**

```
[TYPE] subject

body
```

**예시**

```
[Chore] 기초세팅중

1. 아이콘에셋추가
2. 폰트추가
```
### **TYPE**

- FEAT: 새로운 기능 개발(kotlin 작업)
- FIX: 버그 수정
- CHORE: Gradle이나 설정 세팅할 때

### **Subject**

- 제목은 50글자 내외로 작성하는 것을 권장
- 한글 사용

### **Body**

- Title에 Commit의 충분한 정보가 포함되지 않은 경우 그 내용을 표기
- 각 행은 100글자가 넘지 않도록 주의

### **Footer**

- 참조정보가 있는경우 그 내용을 표기
