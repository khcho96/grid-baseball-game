# 🎯 프리코스 오픈 미션 과제 - 격자 야구 게임 ⚾️

## 📍 게임 소개

### 🧢 게임 개요
- N x N 보드에서 3개의 아웃 지점을 찾아내는 간단한 논리 게임입니다.
- 마무리 투수가 되어 마지막 이닝을 책임지는 상황을 가정합니다.
- 클릭할 때 마다 나오는 힌트를 활용해 보드 위에 숨겨진 3개의 아웃 지점을 찾아내 승리를 쟁취하세요.
- 총 두 가지 모드가 있습니다.
  - 👤 싱글 모드: 혼자서 플레이합니다.
  - 🆚 배틀 모드: 컴퓨터와 대결합니다.

### 🖼️ 게임 화면

#### 메인 화면
<img width="700" height="500" alt="스크린샷 2025-11-20 17 05 31" src="https://github.com/user-attachments/assets/00c68305-fdea-4657-9bc8-29b9dd4b5c6d" />

#### 👤 싱글 모드 플레이 화면
<img width="700" height="500" alt="스크린샷 2025-11-20 17 05 31" src="https://github.com/user-attachments/assets/b3f50ed3-fc6e-4759-9cf2-8ba45f65ba9b" />

#### 🆚 배틀 모드 플레이 화면
<img width="700" height="500" alt="스크린샷 2025-11-20 17 05 31" src="https://github.com/user-attachments/assets/933386d1-9803-4c18-99ee-f726241834c8" />

### 🕹️ 게임 규칙 & 플레이 방식
#### 공통
1. 마무리 투수인 당신은 3개의 아웃을 잡아 이 이닝을 끝내면, 팀을 우승으로 이끕니다.
2. 아웃 지점은 N × N 보드의 서로 다른 세 칸에 무작위로 배치됩니다.
3. 칸을 선택하면 아웃 지점에 대한 힌트가 주어집니다.
- 아웃 지점 선택 시: **아웃**
- 아웃 지점과 상하좌우로 인접한 칸 선택 시: **스트라이크**
- 아웃 지점과 대각선으로 인접한 칸 선택 시: **볼**
- 예) 1S 2B: 상하좌우 인접한 칸에 아웃이 1개, 대각선으로 인접한 칸에 아웃이 2개 존재

#### 싱글 모드
1. 칸을 한 번 선택할 때마다 1구씩 증가합니다.
2. 최소 투구수로 3개의 아웃을 잡는 것을 목표로 합니다.

#### 배틀 모드
1. 칸을 한 번 선택할 때마다 1구씩 증가합니다.
2. 컴퓨터보다 적은 투구 수로 3개의 아웃을 잡는 것을 목표로 합니다.

### 💻 실행 방법
1. `Java 21` 이상이 설치되어 있어야 합니다.
2. 프로젝트를 클론합니다.
```bash
git clone https://github.com/khcho96/grid-baseball-game.git
cd grid-baseball-game
```
3. 빌드 및 실행
#### macOS
```bash
./gradlew clean build
./gradlew run
```
#### Windows
```bash
gradlew.bat clean build
gradlew.bat run
```

### 🛠 기술 스택
- Language: Java 21
- UI: Java Swing
- Build Tool: Gradle 8.13
- Test: JUnit5, AssertJ


## 🚀 기능 구현 목록
> - 내용이 추가 또는 수정될 수 있다.
> - 기능을 구현했다면 해당 기능 목록에 체크한다.

### 🖥️ 메인 화면
#### 
- [x] 사용자에게 두 가지의 게임 모드를 보여준다.
- [x] 싱글 모드 버튼을 클릭하면 싱글 모드 게임을 실행한다.
- [x] 배틀 모드 버튼을 클릭하면 배틀 모드 게임을 실행한다.

### 👤 싱글 모드
#### 화면 구성
- [x] 게임 규칙을 좌측에 배치한다.
- [x] 격자 보드 크기를 변경 할 수 있는 입력란과 버튼을 좌측에 배치한다.
- [x] 격자 보드를 우측에 배치한다.
- [x] 사용자의 게임 진행 상태와 뒤로가기(Back), 재시작(Restart) 버튼을 격자 보드 위에 배치한다.

#### 인터페이스
- [x] 텍스트 입력란에 숫자를 입력하여 격자 보드의 크기를 변경한다.
  - [x] Enter 키와 버튼 모두 동작하도록 구현한다.
- [x] 뒤로가기(Back) 버튼을 클릭하면 메인 화면으로 돌아간다.
- [x] 재시작(Restart) 버튼을 클릭하면 현재 격자 크기의 보드에서 게임이 리셋된다.
- [x] 버튼을 누르면 게임 규칙에 알맞은 결과(예. "1S 2B", "Out!⚾️")를 보여준다.
  - [x] 현재 투구수와 아웃 카운트를 갱신한다.
- [x] 아웃카운트가 3이 되면 우승 메시지와 투구수를 표시하고 게임을 종료한다.

#### 아웃 지점 배치
- [x] 3개의 아웃은 서로 다른 3개의 칸에 랜덤으로 배치한다.

#### 격자 보드 크기 변경
- [x] 사용자가 격자의 크기를 입력한다.
- [x] 격자 크기 입력값을 검증한다.
  - [x] 입력값이 숫자 형태가 아니면 사용자에게 에러메시지를 보여준다.
  - [x] 입력값이 정해진 범위가 아니면 사용자에게 에러메시지를 보여준다.
- [x] 격자 크기 입력값을 정수로 변환한다.
- [x] 격자 크기 입력값이 정상적으로 입력되면 격자 크기를 변경하고 게임 내용을 초기화한다.
  - [x] 정상 입력되었음을 알리는 메시지를 출력한다.

### 🆚 배틀 모드
#### 화면 구성
- [x] 게임 규칙 및 진행 방법을 좌측과 중앙 상단에 배치한다.
- [x] 뒤로가기(Back), 재시작(Restart) 버튼을 우측 상단에 배치한다.
- [x] 격자 보드 크기를 변경 할 수 있는 입력란과 버튼을 뒤로가기, 재시작 버튼 바로 아래에 배치한다.
- [x] 사용자의 격자 보드를 좌측에 배치한다.
- [x] 사용자의 게임 진행 상태를 사용자의 격자 보드 바로 위에 배치한다.
- [x] 컴퓨터의 격자 보드를 우측에 배치한다.
- [x] 컴퓨터의 게임 진행 상태를 컴퓨터의 격자 보드 바로 위에 배치한다.
- [x] 게임 진행 상황을 중앙에 배치한다.
  - [x] 사용자 또는 컴퓨터의 차례 등 게임의 전체적인 진행 상황을 안내한다.

#### 인터페이스
- [x] 텍스트 입력란에 숫자를 입력하여 격자 보드의 크기를 변경한다.
  - [x] Enter 키와 버튼 모두 동작하도록 구현한다.
- [x] 뒤로가기(Back) 버튼을 클릭하면 메인 화면으로 돌아간다.
- [x] 재시작(Restart) 버튼을 클릭하면 현재 격자 크기의 보드에서 게임이 리셋된다.
- [x] 게임 시작 전 컴퓨터가 잡아야 하는 3개의 아웃 지점을 선택한다.
- [x] 사용자 격자 보드의 버튼을 누르면 게임 규칙에 알맞은 결과(예. "1S 2B", "Out!⚾️")를 보여준다.
  - [x] 사용자의 현재 투구수와 아웃 카운트를 갱신한다.
  - [x] 이어서 컴퓨터가 선택 중임을 알리는 메시지 이후에 컴퓨터의 선택 결과를 출력한다.
  - [x] 컴퓨터의 현재 투구수와 아웃 카운트를 갱신한다.
- [x] 사용자와 컴퓨터 둘 중 하나의 아웃카운트가 3이 되면 사용자 입장에서의 결과(승/무/패)를 출력한다.

#### 사용자의 아웃 지점 배치
- [x] 3개의 아웃은 서로 다른 3개의 칸에 랜덤으로 배치한다.

#### 컴퓨터의 아웃 지점 배치
- [x] 사용자가 게임 시작 전 버튼을 3개 클릭하면 내부적으로 아웃 지점을 저장한다.

#### 격자 보드 크기 변경
- [x] 사용자가 격자의 크기를 입력한다.
- [x] 격자 크기 입력값을 검증한다.
  - [x] 입력값이 숫자 형태가 아니면 사용자에게 에러메시지를 보여준다.
  - [x] 입력값이 정해진 범위가 아니면 사용자에게 에러메시지를 보여준다.
- [x] 격자 크기 입력값을 정수로 변환한다.
- [x] 격자 크기 입력값이 정상적으로 입력되면 격자 크기를 변경하고 게임 내용을 초기화한다.
  - [x] 정상 입력되었음을 알리는 메시지를 출력한다.

#### 컴퓨터 사고 알고리즘
- 컴퓨터가 힌트를 활용하여 적절한 버튼을 선택하는 알고리즘을 구현한다.

## 🔥 핵심 구현 내용

### 1. Java Swing을 이용한 UI 구현

### 2. 컴퓨터 사고 알고리즘 구현

#### 선택 알고리즘
- 선택할 수 있는 버튼 중 이웃한 버튼의 숨겨진 정보가 가장 많은 버튼을 선택한다.
- 즉, 이웃한 8개의 버튼 중 아웃 후보이면서 동시에 아직 정보가 공개되지 않은 버튼의 개수가 가장 많은 버튼을 선택한다.
- 예를 들어 처음 시작할 때는 상하좌우 및 대각선 버튼이 모두 비어있는(정보가 공개되지 않은) 버튼을 선택한다.

#### 정보 처리 알고리즘
버튼을 선택해서 나온 정보를 적절히 활용한다.
1. 아웃 지점이 절대 될 수 없는 버튼을 선택 후보에서 제거한다.
- 예1. `1S 0B`이 나온다면 대각선으로 인접한 버튼 4개를 아웃 후보에서 제거한다.
- 예2. `0S 2B`이 나온다면 상하좌우로 인접한 버튼 4개를 아웃 후보에서 제거한다.
2. 각 3개의 아웃 별로 후보를 추가 또는 제거한다.
- 예1. `0S 1B`이 나온다면 대각선으로 인접한 버튼 4개를 해당 아웃 후보에 추가한다.
- 예2. `2S 0B`이 나온다면 상하좌우로 인접한 버튼 4개를 해당 아웃 후보에 추가한다.
- 예3. `Out!⚾️`이 나온다면 클릭한 후보는 최종 아웃 리스트에 추가하고, 해당 아웃의 다른 후보들을 모두 아웃 후보에서 제거한다.


## 📂 프로젝트 구조
```text
grid-baseball-game/
 ├─ build.gradle
 ├─ .gitignore
 ├─ gradlew
 ├─ gradlew.bat
 ├─ gradle/
 │   └─ wrapper/
 │       └─ gradle-wrapper.properties
 └─ src/
     ├─ main/
     │   ├─ java/
     │   │   ├─ application/
     │   │   │   └─ Application.java
     │   │   ├─ controller/
     │   │   │   ├─ GameController.java
     │   │   │   ├─ SingleGameController.java
     │   │   │   └─ BattleGameController.java
     │   │   ├─ service/
     │   │   │   ├─ SingleGameService.java
     │   │   │   └─ BattleGameService.java
     │   │   ├─ domain/
     │   │   │   ├─ GridButton.java
     │   │   │   ├─ GridButtons.java
     │   │   │   ├─ OutZone.java
     │   │   │   └─ SmartComputer.java
     │   │   │   └─ vo/
     │   │   │       ├─ Coordinate.java
     │   │   │       └─ Size.java
     │   │   ├─ dto/
     │   │   │   └─ SizeDto.java
     │   │   ├─ generator/
     │   │   │   └─ RandomGenerator.java
     │   │   ├─ communicator/
     │   │   │   └─ EventCommunicator.java
     │   │   ├─ constant/
     │   │   │   ├─ Constant.java
     │   │   │   └─ ErrorMessage.java
     │   │   ├─ util/
     │   │   │   └─ NumberConverter.java
     │   │   └─ view/
     │   │       ├─ init/
     │   │       │   ├─ InitView.java
     │   │       │   └─ InitEventSetter.java
     │   │       ├─ mode/
     │   │       │   ├─ single/
     │   │       │   │   ├─ SingleGameView.java
     │   │       │   │   └─ SingleGameEventSetter.java
     │   │       │   │   └─ panel/
     │   │       │   │       ├─ SingleGameGridPanel.java
     │   │       │   │       ├─ SingleGameStatePanel.java
     │   │       │   │       ├─ SingleGameResultPanel.java
     │   │       │   │       └─ SingleGameRulePanel.java
     │   │       │   └─ battle/
     │   │       │       ├─ BattleGameView.java
     │   │       │       └─ BattleGameEventSetter.java
     │   │       │       └─ panel/
     │   │       │           ├─ BattleGameUserGridPanel.java
     │   │       │           ├─ BattleGameComputerGridPanel.java
     │   │       │           ├─ BattleGameUserStatePanel.java
     │   │       │           ├─ BattleGameComputerStatePanel.java
     │   │       │           ├─ BattleGameMainStatePanel.java
     │   │       │           └─ BattleGameRulePanel.java
     │   │       └─ util/
     │   │           └─ ComponentSetter.java
     │   └─ resources/
     │       └─ icon/
     └─ test/
         └─ java/
             ├─ domain/
             │   ├─ GridButtonTest.java
             │   ├─ GridButtonsTest.java
             │   └─ OutZoneTest.java
             ├─ domain/vo/
             │   ├─ CoordinateTest.java
             │   └─ SizeTest.java
             └─ util/
                 └─ NumberConverterTest.java
```