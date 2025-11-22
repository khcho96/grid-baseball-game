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
<img width="700" height="500" alt="스크린샷 2025-11-20 17 05 31" src="https://github.com/user-attachments/assets/933386d1-9803-4c18-99ee-f726241834c8" />

#### 🆚 배틀 모드 플레이 화면
<img width="700" height="500" alt="스크린샷 2025-11-20 17 05 31" src="https://github.com/user-attachments/assets/b3f50ed3-fc6e-4759-9cf2-8ba45f65ba9b" />

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
1. 좌표와 인접한 좌표 8개 중 정보가 드러나지 않은 좌표의 개수가 가장 많은 좌표를 선택한다. (가장 적은 횟수의 선택으로 최대한 많은 정보를 얻기 위함이다.)
2. 좌표 선택으로 나온 힌트를 통해 아웃 지점 후보가 나왔다면 다음 선택은 해당 후보 리스트에 있는 좌표 중 하나를 선택한다.
3. 좌표 선택으로 나온 힌트를 통해 아웃 후보에서 걸러진 좌표는 개수에 포함하지 않는다.
4. 최대 개수를 가진 좌표가 여러개라면 그 중 하나를 랜덤으로 선택한다.
- 예1) 3x3 격자 보드에서 처음 선택할 때 가장 많은 인접한 정보를 가진 좌표(빨간 글씨)를 선택한다.
<img width="400" height="400" alt="스크린샷 2025-11-22 15 27 29" src="https://github.com/user-attachments/assets/590b605d-0c8f-4f22-b28b-5a4e17f4d781" />
- 예2) 버튼 선택으로 0S 2B이라는 힌트를 얻었다면 대각선 4개의 좌표는 아웃 지점 후보가 되므로 이 중 하나를 다음 선택으로 결정한다. 이때 가장 많은 숨겨진 인접 정보가 있는 좌표(빨간 글씨)를 선택한다.
<img width="400" height="400" alt="스크린샷 2025-11-22 15 27 57" src="https://github.com/user-attachments/assets/2e396553-c82f-422f-b428-cd05b5814b31" />
- 예3) 5x5 격자 보드에서 어떤 좌표를 선택해 0S 0B이라는 힌트를 얻은 상황에서 인접 정보가 가장 많은 좌표(빨간 글씨)를 선택한다.
<img width="400" height="400" alt="스크린샷 2025-11-22 15 29 14" src="https://github.com/user-attachments/assets/6bb2c7e1-d28f-4a7c-b764-0e5041f764c4" />
- 예4) 4x4 격자 보드에서 처음 선택할 때 가장 많은 인접한 정보를 가진 좌표(빨간 글씨)는 총 4개이고, 그 중 하나를 랜덤으로 선택한다.
  <img width="400" height="400" alt="스크린샷 2025-11-22 15 43 30" src="https://github.com/user-attachments/assets/d40a2e8f-91dc-4e2b-931d-239071acd90d" />

#### 힌트 처리 알고리즘
1. 좌표 선택으로 특정 주변 좌표에 아웃 지점이 존재한다는 힌트를 얻었을 때는 해당 좌표를 아웃 후보 리스트에 추가한다.
2. 좌표 선택으로 특정 주변 좌표에 아웃 지점이 존재하지 않는다는 힌트를 얻었을 때는 해당 좌표를 모든 선택 리스트에서 제거한다.
3. 좌표 선택으로 아웃을 찾았다면 해당 아웃의 나머지 후보들은 절대 아웃 지점이 될 수 없으므로 모든 선택 리스트에서 제거한다.
- 예1) 0S 1B이라는 힌트를 얻었다면 상하좌우 좌표는 모든 선택 리스트에서 제거하고 대각선 좌표 4개는 아웃 후보 리스트에 추가한다.
<img width="400" height="400" alt="스크린샷 2025-11-22 15 52 14" src="https://github.com/user-attachments/assets/5c4906c2-233a-4173-9bd1-d41f4c946736" />
- 예2) 아웃 후보 리스트를 만든 후 그 중 하나의 좌표를 선택해 아웃을 찾았다면 나머지 아웃 후보 좌표는 모든 리스트에서 삭제한다.
  - 1번째 선택 -> **0S 1B**
    - 상하좌우 좌표는 모든 선택지에서 제외하고, 대각선 좌표는 아웃 후보 리스트에 추가한다.
    <img width="400" height="400" alt="스크린샷 2025-11-22 16 16 27" src="https://github.com/user-attachments/assets/0fb042c3-3e8d-4d1b-929b-0f40a49adc19" />
  - 2번째 선택 -> **Out**
    - 선택한 좌표가 아웃이므로 해당 아웃의 후보였던 나머지 3개의 좌표는 모든 선택지에서 제거한다.
      <img width="400" height="400" alt="스크린샷 2025-11-22 16 16 32" src="https://github.com/user-attachments/assets/0dbfdf50-fd7f-4e88-a23a-6018320d5449" />

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