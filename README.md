# 📎 OCR 활용 계약서 정보 확인 및 클라우드 보관 서비스 (팀명 : Fainder)


## 👀 서비스 소개
* 계약서 미작성 및 오작성으로 인한 피해를 방지하기 위한 계약서 정보 확인 서비스
* 사용자가 등록한 계약서의 내용을 간편하게 파악할 수 있는 요약 서비스
* 계약서의 내용 중 위반사항이나 미작성 항목을 파악해서 보여주는 서비스
<br>

## 📅 프로젝트 기간
### 2023.12.08 ~ 2024.01.16
<br>

## ⭐ 주요 기능
* 기능1 : 계약 위반사항 등록 및 보관 서비스
  * 앱에서 원하는 계약서 종류를 선택하고 카메라의 스캐너기능,이미지 및 파일 업로드를 이용하여 계약서를 등록,계약서를 종류별로 분류하고 저장
  
* 기능2 : OCR을 활용한 문서 텍스트 추출 및 요약
  * 사용자가 원하는 계약서의 내용을 OCR을 통해 텍스트로 추출하여 주로 관심 있는 항목을 요약하여 시각적으로 제공함으로써 계약서 내용을 빠르게 파악 가능
  
* 기능3 : 출퇴근시간을 통한 급여 계산
  * 출퇴근 시간을 입력하여 해당 근무시간동안의 총 급여와 캘린터를 통해 매일의 총 급여와 근무정보 확인 가능

* 기능4 : 유저 간 질문 게시판
  * 사용자들 간에 계약 내용에 대한 의견을 공유하고 질문에 답하는 게시판을 제공하여 커뮤니케이션을 촉진

* 기능5 : 계약 미작성 신고 채널 연결
  * 계약을 작성하지 않았거나 누락된 부분이 있을 경우 신고할 수 있는 채널을 제공하여 불완전한 계약을 방지 

<br>

## ⛏ 기술스택
<table>
    <tr>
        <th>구분</th>
        <th>내용</th>
    </tr>
    <tr>
        <td>사용언어</td>
        <td>
            <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white"/>            
            <img src="https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=Python&logoColor=white"/>        
        </td>
    </tr>
    <tr>
        <td>라이브러리</td>
        <td>
            <img src="https://img.shields.io/badge/apachemaven-C71A36?style=for-the-adge&logo=apachemaven&logoColor=white"/>
            <img src="https://img.shields.io/badge/lombok-4285F4?style=for-the-badge&logo=lombok&logoColor=white"/>
            <img src="https://img.shields.io/badge/mybatis-ECD53F?style=for-the-badge&logo=mybatis&logoColor=white"/>
            <img src="https://img.shields.io/badge/gson-000000?style=for-the-badge&logo=gson&logoColor=white"/>
            <img src="https://img.shields.io/badge/flask-000000?style=for-the-badge&logo=flask&logoColor=white"/>           
            <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
            <img src="https://img.shields.io/badge/openai-412991?style=for-the-badge&logo=openai&logoColor=white"/>
        </td>
    </tr>
    <tr>
        <td>개발도구</td>
        <td>
            <img src="https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=Eclipse&logoColor=white"/>
            <img src="https://img.shields.io/badge/VSCode-007ACC?style=for-the-badge&logo=VisualStudioCode&logoColor=white"/>
            <img src="https://img.shields.io/badge/Anaconda-44A833?style=for-the-badge&logo=Anaconda&logoColor=white"/>
            <img src="https://img.shields.io/badge/Jupyter-F37626?style=for-the-badge&logo=Jupyter&logoColor=white"/>
            <img src="https://img.shields.io/badge/Android-34A853?style=for-the-badge&logo=Android&logoColor=white"/>
        </td>
    </tr>
    <tr>
        <td>서버환경</td>
        <td>
            <img src="https://img.shields.io/badge/Apache Tomcat 9.0-D22128?style=for-the-badge&logo=Apache Tomcat&logoColor=white"/> 
        </td>
    </tr>
    <tr>
        <td>데이터베이스</td>
        <td>
            <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white"/>
            <img src="https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=MongoDB&logoColor=white"/>
            <img src="https://img.shields.io/badge/amazons3-569A31?style=for-the-badge&logo=amazons3&logoColor=white"/>
        </td>
    </tr>
    <tr>
        <td>협업도구</td>
        <td>
            <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white"/> 
            <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white"/>
        </td>
    </tr>
    <tr>
        <td>디자인도구</td>
        <td>
            <img src="https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=Figma&logoColor=white"/>
        </td>
    </tr>
</table>
<br>

## ⚙ 시스템 아키텍처(구조)
![그림3](https://github.com/2023-SMHRD-KDT-AI-3/Fainds/assets/144747174/af83f5cd-affa-4389-a835-b460137f5ffa)

## 📌 SW유스케이스
![FAINDS](https://github.com/2023-SMHRD-KDT-AI-3/Fainds/assets/144747174/306beca9-8a36-42bf-90a0-310f1a2e23dc)

## 📌 서비스 흐름도
![그림4](https://github.com/2023-SMHRD-KDT-AI-3/Fainds/assets/144747174/a9c4e821-f3a5-428a-9700-268c9136c5cd)

## 📌 ER다이어그램
![KakaoTalk_20240109_144418016](https://github.com/2023-SMHRD-KDT-AI-3/Fainds/assets/144747174/f8bbb6e2-3d8a-4900-b75b-2b11e48a6629)

## 🖥 화면 구성
- ### 스플래시 페이지
![스플래시](https://github.com/2023-SMHRD-KDT-AI-3/Fainds/assets/144747174/519e9710-b249-4ee2-b0db-a14e2a0c7bd8)
<hr>

- ### 로그인/회원가입/비밀번호 찾기 페이지
![로그인,회원가입,찾기](https://github.com/2023-SMHRD-KDT-AI-3/Fainds/assets/144747174/5c5d0543-e500-42db-aaea-7f1f7c5844c1)
<hr>

- ### 계약서 보관함/ 상세보기/ 계약서를 이용한 질문
![계약서,상세보기,질문](https://github.com/2023-SMHRD-KDT-AI-3/Fainds/assets/144747174/3843d506-8170-4855-b98d-04678ad45716)
<hr>

- ### 계약서 종류선택/등록방식 선택/계약서 간편보기
![제목 없는 디자인](https://github.com/2023-SMHRD-KDT-AI-3/Fainds/assets/144747174/47cbb57c-595c-48b1-a606-b425540cde8e)
<hr>

- ### 질문 게시판
![질문게시판](https://github.com/2023-SMHRD-KDT-AI-3/Fainds/assets/144747174/1ea54724-12b6-421b-a665-7448c757cf0c)
<hr>

- ### 게시글 수정 페이지
![게시글 수정](https://github.com/2023-SMHRD-KDT-AI-3/Fainds/assets/144747174/cf382ad5-0958-4a8a-ba4f-8f1e5435ed6e)
<hr>

- ### 더보기 페이지
![더보기](https://github.com/2023-SMHRD-KDT-AI-3/Fainds/assets/144747174/73ebe6e3-ecd5-4a00-bc33-2fb1aae388d6)
<hr>

- ### 이메일 수정 페이지
![이메일 수정](https://github.com/2023-SMHRD-KDT-AI-3/Fainds/assets/144747174/15a1a407-842b-4250-a5b3-5ca9205012e7)
<hr>

- ### 비밀번호 수정 페이지
![비밀번호 수정](https://github.com/2023-SMHRD-KDT-AI-3/Fainds/assets/144747174/9a4c0d64-7e46-4a7d-bd02-b464de01596d)
<hr>

- ### 팁 게시판
![팁게시판](https://github.com/2023-SMHRD-KDT-AI-3/Fainds/assets/144747174/22b1bc53-d63f-496b-9240-1f2758e630dc)
<hr>

- ### 급여계산기/근무기록 등록/근무기록 상세보기
![제목 없는 디자인](https://github.com/2023-SMHRD-KDT-AI-3/Fainds/assets/144747174/f0461cff-bd3c-492f-a32a-480bdc779b0d)
<hr>

## 👨‍👩‍👦‍👦 팀원 역할
<table>
  <tr>
    <td align="center"><img src="https://item.kakaocdn.net/do/fd49574de6581aa2a91d82ff6adb6c0115b3f4e3c2033bfd702a321ec6eda72c" width="100" height="100"/></td>
    <td align="center"><img src="https://mb.ntdtv.kr/assets/uploads/2019/01/Screen-Shot-2019-01-08-at-4.31.55-PM-e1546932545978.png" width="100" height="100"/></td>
    <td align="center"><img src="https://mblogthumb-phinf.pstatic.net/20160127_177/krazymouse_1453865104404DjQIi_PNG/%C4%AB%C4%AB%BF%C0%C7%C1%B7%BB%C1%EE_%B6%F3%C0%CC%BE%F0.png?type=w2" width="100" height="100"/></td>
    <td align="center"><img src="https://i.pinimg.com/236x/ed/bb/53/edbb53d4f6dd710431c1140551404af9.jpg" width="100" height="100"/></td>
    <td align="center"><img src="https://pbs.twimg.com/media/B-n6uPYUUAAZSUx.png" width="100" height="100"/></td>
  </tr>
  <tr>
    <td align="center"><strong>신현욱</strong></td>
    <td align="center"><strong>고영준</strong></td>
    <td align="center"><strong>박재영</strong></td>
    <td align="center"><strong>박주영</strong></td>
    <td align="center"><strong>윤대호</strong></td>
  <tr>
    <td align="center"><b>프로젝트 총괄(PM), DB 설계, 화면 설계, 주요기능 구현,Front-End,Back-End</b></td>
    <td align="center"><b>Back-end, 데이터 흐름 설계,서버통신,주요기능 및 알고리즘 구현</b></td>
    <td align="center"><b>Back-end, DB 설계, 서버통신 및 기능구현</b></td>
    <td align="center"><b>Front-end,Back-End ,UI 설계</b></td>
    <td align="center"><b>Front-end,Back-End</b></td>
  </tr>
    </tr>
    <tr>
    <td align="center"><strong>뭐</strong></td>
    <td align="center"><strong>했</strong></td>
    <td align="center"><strong>는</strong></td>
    <td align="center"><strong>지</strong></td>
    <td align="center"><strong>?</strong></td>
  </tr>
  <tr>
    <td align="center"><a href="https://github.com/hw-Shin" target='_blank'>github</a></td>
    <td align="center"><a href="https://github.com/자신의username작성해주세요" target='_blank'>github</a></td>
    <td align="center"><a href="https://github.com/ttuttae" target='_blank'>github</a></td>
    <td align="center"><a href="https://github.com/자신의username작성해주세요" target='_blank'>github</a></td>
    <td align="center"><a href="https://github.com/dao7179" target='_blank'>github</a></td>
  </tr>
</table>

## 🤾‍♂️ 트러블슈팅

![그림1](https://github.com/2023-SMHRD-KDT-AI-3/Fainds/assets/144747174/cad2e542-2086-4dd4-aec6-e0c1987178b2)
![그림6](https://github.com/2023-SMHRD-KDT-AI-3/Fainds/assets/144747174/3515a7a1-aa2d-4b5c-b052-163076714f0f)
![그림7](https://github.com/2023-SMHRD-KDT-AI-3/Fainds/assets/144747174/5b4798c7-dbce-4d7d-94fa-06f4522bcd0f)
