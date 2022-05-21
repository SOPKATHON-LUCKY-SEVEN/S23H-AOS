# &#128154; *23#-AOS

<img src="https://user-images.githubusercontent.com/81347125/169655860-9be91b10-577e-4a02-8b9e-071860b510b1.png" width = "60%">

##### 서비스명 : 별이삼샵
> 너무 사소한 것 같아 속으로만 삼켰던, 또는 부끄러워서 표현하지 못했던 감사의 마음이 있나요?  
> *23#을 누르고 그 마음을 전달하세요.  
> 낮이나 밤이나 항상 떠 있는 별들처럼, 우리가 표현하지 못하는 동안에도 곁에 있던 감사의 순간들.  
> 친구/가족/연인에게 고마움을 전달해서 평범했던 서로의 일상에 밝은 별이 되어주세요.  
    
    
    
----  

### 1) 역할 분담

| Task           |스플래쉬 뷰|회원가입 뷰|메인 뷰|편지 전체 뷰|편지 상세 뷰|편지 쓰기 뷰|
|----------------|---------------|---------------|----------------|-----------|-----------|-----------|
| 강원용 |　-|<li> [x] Layout</li><li> [x] Inflate</li><li> [x] Remote</li>|　-|<li> [x] Inflate</li><li> [x] Remote</li>|　-|　-|
| 권용민 | <li> [x] Inflate</li>|　-|<li> [x] Layout</li><li> [x] Inflate</li><li> [x] Remote</li>|　-|　-|　-|
| 김세훈 |　-|　-|　-|<li> [x] Layout</li>|<li> [x] Layout</li><li> [x] Inflate</li><li> [x] Remote</li>|<li> [x] Layout</li><li> [x] Inflate</li><li> [x] Remote</li>|

### 2) Git Convention
<a href="https://github.com/SOPKATHON-LUCKY-SEVEN/S23H-AOS/blob/main/README/Git_Convention.md"> Git Convention </a>


### 3) Code Naming Convention
<a href="https://github.com/SOPKATHON-LUCKY-SEVEN/S23H-AOS/blob/main/README/Coding_Convention.md"> Code Naming Convention </a>


### 4) 브랜치 전략
- 각자 고유 브랜치 생성

### 5) 각자 맡은 뷰 설명

![Starlist_Detail](https://user-images.githubusercontent.com/81347125/169671839-599d0221-cb15-4eb6-9a0c-6effb54ab423.png)
- 용민 : 다이얼로그를 통해 편지 확인 가능
![Starlist](https://user-images.githubusercontent.com/81347125/169671841-5428a5ee-4379-4f04-84ed-6c5fff7fd5d9.png)
- 세훈,원용 : 편지가 도착하는 뷰, 해당 별모양 아이콘 클릭 시 다이얼로그 호출
![Write_Writing](https://user-images.githubusercontent.com/81347125/169671842-a64d835a-b52f-4bbf-84eb-93f5497a5468.png)
- 세훈 : 편지내용 쓰는 뷰, 해당 편지텍스트가 비어있을 경우 버튼 비활성화
![Main](https://user-images.githubusercontent.com/81347125/169671844-c1c7abb0-e751-4f83-bc6a-db9efb3d1e4b.png)  
- 용민 : 리사이클러뷰 구현 및 인텐트전달
![Name_ write](https://user-images.githubusercontent.com/81347125/169671845-78d362af-778f-45ce-abdc-30c6ec667a60.png)  
- 원용 : 회원가입 뷰, 자동로그인 구현
![Splash](https://user-images.githubusercontent.com/81347125/169671846-1e611306-e5ca-48c8-9ccf-17d1f0538ff9.png)  
- 원용 : 스플래쉬 



