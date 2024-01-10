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
![그림1](https://github.com/2023-SMHRD-KDT-AI-3/Fainds/assets/144747174/34fc6333-df60-4278-acf6-ae4c6354f39b)

## 📌 SW유스케이스
![FAINDS](https://github.com/2023-SMHRD-KDT-AI-3/Fainds/assets/144747174/306beca9-8a36-42bf-90a0-310f1a2e23dc)

## 📌 서비스 흐름도
![그림2](https://github.com/2023-SMHRD-KDT-AI-3/Fainds/assets/144747174/942f3fd1-20e7-4109-a23c-149fe41780bb)

## 📌 ER다이어그램
![KakaoTalk_20240109_144418016](https://github.com/2023-SMHRD-KDT-AI-3/Fainds/assets/144747174/f8bbb6e2-3d8a-4900-b75b-2b11e48a6629)

## 🖥 화면 구성
이미지
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
  
* 문제1<br>
 문제점 설명 및 해결방안
 
* 문제2<br>
 문제점 설명 및 해결방안
