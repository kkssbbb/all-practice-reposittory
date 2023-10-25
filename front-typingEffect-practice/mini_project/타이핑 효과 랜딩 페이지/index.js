let target = document.querySelector('#dynamic');


function randomString(){
    let stringArr = ['Learn to HTML', 'Learn to CSS', 'Learn to JavaScript'
];

let selctString = stringArr[Math.floor(Math.random() * stringArr.length)]

let selectStringArr = selctString.split('');

 return selectStringArr;
}

function blink(){
    target.classList.toggle('active');
}
setInterval(blink, 500 )

function resetTyping(){
    target.textContent = '';
    active(randomString());
}


function active(randomArr){
    if( randomArr.length>0){
        target.textContent += randomArr.shift();
        setTimeout(function(){
            active(randomArr);
        }, 80);
    }else{
        setTimeout(resetTyping, 3000);
    }
}

active(randomString());
