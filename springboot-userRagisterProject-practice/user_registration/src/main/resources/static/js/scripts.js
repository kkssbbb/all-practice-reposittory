//엑셀 다운로드
function userListDownload() {
  console.log("userListDownload() 호출 테스트");
}

//검색 이벤트 처리
function searchBtn() {
  console.log("searchBtn()호출로그");
  serachForm.submit();
}

// DOM 로드 후 실행되는 함수
document.addEventListener("DOMContentLoaded", function () {
  // Save 버튼 클릭 시
  document
    .getElementById("save-button")
    .addEventListener("click", function (event) {
      event.preventDefault();

      console.log("레지스터 호출로그");
      register();
    });

  // Save 2 버튼 클릭 시
  document
    .getElementById("update-button")
    .addEventListener("click", function (event) {
      event.preventDefault();
      updateUser(userId);
    });
});

// register() 시작
function register() {
  const name = document.getElementById("name").value;
  const age = document.getElementById("age").value;
  const gender = document.getElementById("gender");
  const job = document.getElementById("occupation").value;

  console.log("레지스터 호출로그");

  if (!name || !age || !gender || !job) {
    console.log("널 값 조건 로그");
    alert("입력 값을 모두 넣어주세요");
    return;
  }
  if (isNaN(age)) {
    console.log("나이 조건문 테스트");
    alert("숫자를 입력해 주세요");
    return;
  }
  alert("사용자를 등록 하시겠습니까?");

  const user = {
    name: name,
    age: age,
    gender: gender,
    job: job,
  };

  /*   fetch("/page/register", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  })
    .then((response) => response.json())

    .catch((error) => {
      // 등록에 실패한 경우 처리
      console.error("Failed to register user: ", error);
      // 에러 메시지
    }); */

  axios
    .post("/page/register", user, {
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((res) => {
      console.log(res.status === 200);
      if (res.status === 200) {
        alert("유저 등록이 완료되었습니다.👍");
        location.reload();
        //   return res.json();
      }
    })
    .catch((err) => {
      console.error("Failed to register user: ", err);
      alert("유저 등록에 실패하였습니다.");
    });
}

// register() 끝

// 행 업데이트 함수
function edit(id, name, age, gender, job) {
  console.log("업데이트로우 함수 호출");
  console.log(id);
  console.log(name);
  console.log(age);
  console.log(gender);
  console.log(job);
  window.userId = id;
  let userName = document.getElementById("name");
  let userAge = document.getElementById("age");
  let userGender = document.getElementById("gender");
  let userJob = document.getElementById("occupation");
  let titleNmae = document.getElementById("title-name");

  titleNmae.innerHTML = "사용자 수정";

  userName.value = name;
  userAge.value = age;
  userGender.value = gender;
  userJob.value = job;

  if (gender === "여자") {
    var femaleRadio = document.getElementById("female");
    femaleRadio.checked = true;
    var maleRadio = document.getElementById("male");
    maleRadio.checked = false;
  }
  if (gender === "남자") {
    var femaleRadio = document.getElementById("female");
    femaleRadio.checked = false;
    var maleRadio = document.getElementById("male");
    maleRadio.checked = true;
  }
}

// Remove 함수
function remove(userId) {
  console.log("리무브 함수 호출로그");

  /*   if (confirmation) {
    fetch("/page/remove?userId=" + userId, {
      method: "POST",
    })
      .then((response) => {
        if (response.ok) {
          alert("삭제 되었습니다👍");
          location.reload(); // 페이지 새로고침
          // 원하는 동작 수행
        } else {
          alert("요청이 실패하였습니다.👍");
          // 실패 처리 로직 구현
        }
      })
      .catch((error) => {
        alert("오류가 발생하였습니다.👍", error);
        // 에러 처리 로직 구현
      });
  } */

  let confirmation = confirm("정말로 삭제하시겠습니까?");
  if (confirmation) {
    axios("/page/remove?userId=" + userId, {
      method: "POST",
    })
      .then((response) => {
        if (response.status === 200) {
          alert("삭제 되었습니다👍");
          location.reload(); // 페이지 새로고침
          // 원하는 동작 수행
        } else {
          alert("요청이 실패하였습니다.👍");
          // 실패 처리 로직 구현
        }
      })
      .catch((error) => {
        alert("오류가 발생하였습니다.👍", error);
        // 에러 처리 로직 구현
      });
  }
}
// 업데이트 함수
function updateUser() {
  console.log("업데이트 함수 호출 로그");
  const userName = document.getElementById("name");
  const userAge = document.getElementById("age");
  const userGender = document.getElementById("gender");
  const userJob = document.getElementById("occupation");

  const updatedUser = {
    userId: window.userId, // 전역 변수로 설정한 userId 사용
    name: userName.value,
    age: userAge.value,
    gender: userGender.value,
    job: userJob.value,
  };
  /* 
  fetch(`/page/update?userId=${window.userId}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(updatedUser),
  })
    .then((response) => {
      if (response.ok) {
        // 업데이트 성공
        alert("업데이트 성공");
        location.reload(); // 페이지 새로고침
        // 새로고침 또는 필요한 동작 수행
      } else {
        // 업데이트 실패
        alert("업데이트 실패했습니다");
        // 실패 처리 또는 필요한 동작 수행
      }
    })
    .catch((error) => {
      alert("업데이트 에러", error);
      // 에러 처리 또는 필요한 동작 수행
    });
 */
  /*  axios 로 적용 코드 */
  console.log(updatedUser.name);
  if (!userName || !userAge || !userGender || !userJob) {
    console.log("널 값 조건 로그");
    alert("입력 값을 모두 넣어주세요");
    return;
  }
  if (isNaN(userAge.value)) {
    console.log(userAge.value);
    console.log("나이 조건문 테스트");
    alert("숫자를 입력해 주세요");
    return;
  }
  alert("사용자를 수정 하시겠습니까?");

  axios
    .post(`/page/update?userId=${userId}`, updatedUser, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((response) => {
      console.log("response.status : ", response.status);
      if (response.status === 200) {
        // 업데이트 성공
        alert("업데이트 성공👍");
        location.reload(); // 페이지 새로고침
      } else {
        // 업데이트 실패
        alert("업데이트 실패했습니다👍");
        // 실패 처리
      }
    })
    .catch((error) => {
      alert("업데이트 에러👍", error);
      // 에러 처리
    });
}
