const forWhom = document.getElementById('for__whom-bar'); 
const forWhomMain = document.getElementById('for__whom__main'); 
const forWhomImg = document.getElementById('for__whom-img'); 

const howWeDo = document.getElementById('how__we__do-bar'); 
const howWeDoMain = document.getElementById('how__we__do__main'); 
const howWeDoImg = document.getElementById('how__we__do-img'); 

const mng = document.getElementById('management__tech-bar'); 
const mngMain = document.getElementById('management__tech__main'); 
const mngImg = document.getElementById('management__tech-img'); 

const criteria = document.getElementById('criteria-bar'); 
const criteriaMain = document.getElementById('criteria__main'); 
const criteriaImg = document.getElementById('criteria-img'); 

const targets = document.getElementById('targets-bar'); 
const targetsMain = document.getElementById('targets__main'); 
const targetsImg = document.getElementById('targets-img'); 

const hotels = document.getElementById('hotels-bar'); 
const hotelsMain = document.getElementById('hotels__main'); 
const hotelsImg = document.getElementById('hotels-img'); 

forWhom.onclick = () => toggleInfo(forWhom, forWhomMain, forWhomImg);
howWeDo.onclick = () => toggleInfo(howWeDo, howWeDoMain, howWeDoImg);
mng.onclick = () => toggleInfo(mng, mngMain, mngImg);
criteria.onclick = () => toggleInfo(criteria, criteriaMain, criteriaImg);
targets.onclick = () => toggleInfo(targets, targetsMain, targetsImg);
hotels.onclick = () => toggleInfo(hotels, hotelsMain, hotelsImg);


function toggleInfo(subject, content, img) { 
	const currCondition = window.getComputedStyle(content, null).getPropertyValue("display");
	

	if(currCondition === 'none'){
		content.style.display = 'flex';
		subject.style.backgroundImage = "url('/img/bg/mng-bar-open.svg')";
		img.src="/img/icons/minus.svg";
		img.style.marginTop = "20px";
	} else {
		content.style.display = 'none';
		subject.style.backgroundImage = "url('/img/bg/mng-bar-close.svg')";
		img.src="/img/icons/plus.svg";
		img.style.marginTop = "8px";
	}
}



document.getElementById("imageid").src="../template/save.png";

