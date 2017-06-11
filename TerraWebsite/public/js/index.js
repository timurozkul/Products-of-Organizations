const pauseButton = document.getElementById('container');
const slides = document.querySelectorAll('.container .slide');
const slideButtons = document.querySelectorAll('.slide__buttons .button');

const button1 = document.getElementById('button1');
const button2 = document.getElementById('button2');
const button3 = document.getElementById('button3');

let currentSlide = 0;
let slideInterval = setInterval(nextSlide,5000);

function nextSlide(onClick) {
    slides[currentSlide].className = 'slide';
    slideButtons[currentSlide].className = 'button';

    currentSlide = typeof onClick === 'number' ? onClick : (currentSlide+1)%slides.length;

    slides[currentSlide].className = 'slide showing';
    slideButtons[currentSlide].className = 'button selected';
}

function pauseSlideshow() { clearInterval(slideInterval); }
function playSlideshow() { slideInterval = setInterval(nextSlide,2000); }

pauseButton.onmouseover = () => {  pauseSlideshow(); };
pauseButton.onmouseout = () => {  playSlideshow(); }
button1.onclick = () => { nextSlide(0); }
button2.onclick = () => { nextSlide(1); }
button3.onclick = () => { nextSlide(2); }