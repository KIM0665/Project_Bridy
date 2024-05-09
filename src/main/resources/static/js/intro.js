let master;
// 인트로 영역 애니메이션 옵션 설정
const animationOptions = {
  ease: 'expo.inOut'  // Easing 함수를 'expo.inOut'으로 설정
};

// 인트로 애니메이션 함수 정의
const introAnimation = () => {
  // GSAP 타임라인 생성
  const tl = gsap.timeline({
    defaults: {
      ease: animationOptions.ease,  // 기본적으로 사용할 Easing 함수 설정
      duration: 1,  // 기본 애니메이션 지속 시간
    }
  });

  // 1. 제목이 위로 이동하면서 나타남
  tl.to('.intro__title', {
    duration: 1.5,  // 애니메이션 지속 시간
    y: 0,  // y축 방향으로 0만큼 이동
    autoAlpha: 1,  // 투명도를 1로 설정하여 나타남
    delay: 0.5,  // 0.5초의 딜레이
  })
  // 2. 왼쪽, 오른쪽 배경이 나타나면서 확장됨
  .to('.intro__background--left, .intro__background--right', {
    scaleX: 1,  // x축 방향으로 1만큼 확장
  })
  // 3. 배경이 위로 축소되고 투명도가 0이 됨
  .to('.intro__background--left, .intro__background--right', {
    scaleY: 0,  // y축 방향으로 0만큼 축소
    transformOrigin: 'top center',  // 축소 기준을 상단 중앙으로 설정
  })
  // 4. 제목이 다시 위로 이동하면서 투명도가 0으로 설정됨
  .to('.intro__title', {
    duration: 1.5,  // 애니메이션 지속 시간
    y: -60,  // y축 방향으로 -60만큼 이동
    autoAlpha: 0,  // 투명도를 0으로 설정하여 사라짐
  }, '-=0.6')  // 앞서 진행된 애니메이션의 0.6초 전에 시작
  // 5. 인트로 영역이 위로 이동하면서 화면을 떠남
  .to('.intro', {
    y: '-100%',  // y축 방향으로 -100%만큼 이동하여 사라짐
  }, '-=0.5');  // 앞서 진행된 애니메이션의 0.5초 전에 시작

  // 생성된 타임라인 반환
  return tl;
}

// 요소들을 기울이는 애니메이션 함수
const skewInElements = elements => {
  const tl = gsap.timeline();

  // 주어진 요소들에 대해 애니메이션 설정
  tl.from(elements, {
    duration: 1,  // 애니메이션 지속 시간
    ease: animationOptions.ease,  // Easing 함수 설정
    skewY: -5,  // y축 방향으로 -5만큼 기울임
    autoAlpha: 0,  // 투명도를 0으로 설정하여 나타남
    y: 40,  // y축 방향으로 40만큼 이동
  });

  // 생성된 타임라인 반환
  return tl;
}

// 요소들을 페이드인하는 애니메이션 함수
const fadeInElements = elements => {
  const tl = gsap.timeline();

  // 주어진 요소들에 대해 애니메이션 설정
  tl.from(elements, {
    duration: 1,  // 애니메이션 지속 시간
    ease: animationOptions.ease,  // Easing 함수 설정
    y: '20px',  // y축 방향으로 20px만큼 이동
    autoAlpha: 0,  // 투명도를 0으로 설정하여 나타남
    stagger: 0.1,  // 요소들 간에 0.1초 간격을 두고 순차적으로 애니메이션 실행
  });

  // 생성된 타임라인 반환
  return tl;
}

// 메인 타임라인 정의
master = gsap.timeline({
  paused: false,  // 타임라인을 자동으로 실행
  delay: 0.2  // 0.2초의 딜레이
});

// 메인 타임라인에 각 애니메이션 추가
master
  .add(introAnimation())  // 인트로 애니메이션
  .add(fadeInElements('.header'))  // 헤더 요소들의 페이드인 애니메이션
  .add(skewInElements('.header'), '-=1');  // 헤더 요소들을 기울이는 애니메이션 (앞선 애니메이션의 1초 전에 시작)




        // nav-link 클래스명 선택(여러 선택됨)
        let navLink = document.querySelectorAll('.nav-link');

        // 각 요소마다 이벤트 요소 추가 하기 위한
        navLink.forEach(function(navLink){

        // 이벤트 추가하기
        navLink.addEventListener('click', function(event){

            event.preventDefault();

            console.log(navLink);

            // nav-item을 클릭할 때마다 다시 가져오기
            let navItem = document.querySelectorAll('.nav-item');

            // 다른 모든 nav-item에서 active 클래스 제거
            navItem.forEach(function(item) {
                item.classList.remove('active');
            });
            console.log(navLink);
            // 현재 클릭된 nav-link의 부모 요소인 nav-item에 active 클래스 추가
            this.parentNode.classList.add('active');

            let form = document.createElement('form');

            form.setAttribute('method', 'get');
            form.setAttribute('action', this.getAttribute('href'));

            document.body.appendChild(form);

            form.submit();

    });
});