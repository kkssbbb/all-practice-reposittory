const express = require('express')
const app = express()
const port = 3000

const bodyParser = require('body-parser')
const cookieParser = require('cookie-parser')
const config = require('./config/key');
//이전에 만든 user 모델을 가져옴
const {User} = require('./models/User')

const {auth} = require('./middleware/Auth')




//bodyParser 옵션
 app.use(bodyParser.urlencoded({extended: true})); //
 app.use(bodyParser.json());
// cookieParser
  app.use(cookieParser());

//몽구스 = 몽고디비를 편리하게 이용하게 해주는 툴
const mongoose = require('mongoose');
const { application } = require('express')
  mongoose.connect(config.mongoURI)
.then(() => console.log('MongoDB Connected....')).catch(err => console.log(err))


app.get('/', (req, res) => {
  res.send('Hello World! \n 이것도 돼? 돼돼돼돼?')

})

//회원 가입 기능
app.post('/api/user/register',(req,res)=> {
    //회원 가입 할때 필요한 정보들을 client에서 가져오면
    //그것들을 데이터 베이스에 넣어준다.

    const user = new User(req.body); //bodyParser를 이용해서 클라이언트 데이터를 body에 담아 서버로 전송한다.
    user.save((err,doc)=>{
      if(err) return res.json({success: false,err})
      return res.status(200).json({success: true})
    });

})

//로그인 기능

app.post('/api/user/login',(req,res)=>{

  User.findOne({email: req.body.email }, (err, user)=>{
      //요청된 이메일을 데이터 베이스에 있는지 찾는다.
    if(!user){
      return res.json({
        loginSucess: false,
        message: "등록되지 않은 이메일 입니다."
      })
    }//요청된 비밀번호가 맞는 비밀번호인지 확인한다.
    user.comparePassword(req.body.password , (err, isMatch) =>{
        if(!isMatch)
        return res.json({ loginSucess: false, message: "비밀번호가 틀렸습니다."})

      //비밀번호 까지 맞다면 토큰을 생성하기.
      user.generateToken((err, user) => {
        if (err) return res.status(400).send(err);

        // 토큰을 저장한다.  어디에 ?  쿠키 , 로컳스토리지 
        res.cookie("x_auth", user.token)
          .status(200)
          .json({ loginSuccess: true, userId: user._id })
          
        })

     })

  })
})

// role 1 어드민 role 2 특정 부서 어드민
// role 0 -> 일반유저 role 0이 아니면 관리자 

//Auth 관련 //중간 미들웨어인 auth가 로그인 여부 처리
app.get('/api/user/auth',auth,(req,res,next)=>{

  //여기 까지 미들웨어를 통과해 왔다는 얘기는 Authentication 이  True 라는 말.
  res.status(200).json({
    _id: req.User._id,
    isAdmin: req.user.role === 0? false : true,
    isAuth: true,
    email: req.User.email,
    name: req.user.name,
    lastname: req.user.lastname,
    role: req.user.role,
    image: req.user.image
      
  });

})

//데이터베이스에 유저토큰을 지워서 로그인 유지가 끊기게 만들어 로그아웃을 한다.
//이렇게 할 수 있는이유는 클라이언트에서 유저토큰을 가져와 데이터 베이스에 있는 토큰을 확인해서 로그인 인증을 하기 때문이다.
//로그아웃 (라우터) 기능
app.get('/api/user/logout', auth, (req, res) => {
  // console.log('req.user', req.user)
  User.findOneAndUpdate({ _id: req.user._id },
    { token: "" }
    , (err, user) => {
      if (err) return res.json({ success: false, err });
      return res.status(200).send({
        success: true
      })
    })
})



app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
  console.log('이것도 돼?')
})
