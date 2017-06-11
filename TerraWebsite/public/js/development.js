const whatIs = document.getElementById('what__is-bar'); 
const whatIsMain = document.getElementById('what__is__main'); 
const whatIsImg = document.getElementById('what__is__img'); 

const phases = document.getElementById('phases__bar'); 
const phasesMain = document.getElementById('phases__main'); 
const phasesImg = document.getElementById('phases__img'); 

const commitment = document.getElementById('our__commitment__bar'); 
const commitmentMain = document.getElementById('our__commitment__main'); 
const commitmentImg = document.getElementById('our__commitment__img'); 

const properties = document.getElementById('properties__bar'); 
const propertiesMain = document.getElementById('properties__main'); 
const propertiesImg = document.getElementById('properties__img'); 


whatIs.onclick = () => toggleInfo(whatIs, whatIsMain, whatIsImg);
phases.onclick = () => toggleInfo(phases, phasesMain, phasesImg);
commitment.onclick = () => toggleInfo(commitment, commitmentMain, commitmentImg);
properties.onclick = () => toggleInfo(properties, propertiesMain, propertiesImg);



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

