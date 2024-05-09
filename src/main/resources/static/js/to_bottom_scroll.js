// footer 영역 
// 년도 계산 (2024년)
const thisYear = document.querySelector('.more');

thisYear.textContent = new Date().getFullYear();

const headerLogo = document.querySelector('.logo');

headerLogo.addEventListener('click', function(){
  location.href = 'main.html';
});

// 스크롤 올리기 위함 용도
const toTopEl = document.querySelector('#to-top');

window.addEventListener('scroll', _.throttle(function () {
  if (window.scrollY > 500) { // 해당 스크롤 내린 위치값이 700보다 크다면
    gsap.to(toTopEl, .6, { // toTopEl 적용 대상, 0.6초동안 애니메이션 진행
      opacity: 1,
      display: 'block'
    });
    toTopEl.classList.add('show'); // 클래스추가 : display : block을 하기 위함
  } else {
    gsap.to(toTopEl, .6, {
      opacity: 0,
      display: 'none'
    });
    toTopEl.classList.remove('show');
  }
}, 300));


// 해당 버튼 클릭 시 맨 상단에 올라감
toTopEl.addEventListener('click', function () {
  gsap.to(window, .7, {
    scrollTo: 0
  });
});

// 스크롤 내리기 위함

const body = document.body; // 전체 스크롤 값 파악
let height = Math.max(body.scrollHeight);
const btnEl = document.querySelector('#to-bottom');

window.addEventListener('scroll', _.throttle(function () {
  if (window.scrollY < 200) { // 해당 스크롤 내린 위치값이 700보다 크다면
    gsap.to(btnEl, .6, { // toTopEl 적용 대상, 0.6초동안 애니메이션 진행
      opacity: 1,
      display: 'block',
    });
    btnEl.classList.add('show'); // 클래스추가 : display : block을 하기 위함
  } else {
    gsap.to(btnEl, .6, {
      opacity: 0,
      display: 'none'
    });
    btnEl.classList.remove('show');
  }
}, 300));

btnEl.addEventListener('click', function () {
  gsap.to(window, .7, {
    scrollTo: height
  });
});



