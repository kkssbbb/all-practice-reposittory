const mongoose = require('mongoose');

//몽고디비에 저장된 비밀번호를 암호화하기위한 라이브러리 "bcrypt"
const bcrypt = require('bcrypt');
const saltRounds = 10 //10자리의 솔트로 암호화
const jwt = require('jsonwebtoken'); // jsonwebtoken 라이브러리 인폴트

const userSchema = mongoose.Schema({

    nmae:{
        type: String,
        maxlength: 15
    },
    email: {
        type: String,
        trim: true,
        unique: 1
    },
    password:{
            type: String,
            minlength: 5
    },
    lastname: {
        type: String,
        maxlength: 15
    },
    role:{
        type: Number,
        default: 0
    },
    image: String,
    token:{
        type: String
    },
    tokenExp:{
        type:Number
    }

})

userSchema.pre('save' , function (next){

    var user = this;
    //패스워드를 변경할때만 암호화를 해준다.
    if(user.isModified('password')){
    //비크립트에 솔트를 가져온다 (솔트를 가져와서 암호화시키기위ㅏ해 솔트라운드를 사용)
    bcrypt.genSalt(saltRounds, function(err, salt){
        if(err) return next(err)
        bcrypt.hash(user.password, salt, function(err,hash){
            if(err) return next(err)
            user.password = hash
            next();
        })
    })
} else{
    next();
}
})

//비밀번호 비교 메서드
userSchema.methods.comparePassword = function(plainPassword, cd){

    //plainPassword 1234567 과 솔트로 암호화된 비밀번호를 같은지 비교
    //암호화된 솔트비번을 복구 할 수 없기 떄문에 기존 비밀번호를 암호화 해서 암호화된 비밀번호와 비교한다.
    bcrypt.compare(plainPassword, this.password, function(err, isMatch){
        if(err) 
        return  cd(err);
         cd(null, isMatch);
    } )
}

//jsonwebtoken라이브러리를 이용해서 토큰 생성
userSchema.methods.generateToken = function (cb) {
    var user = this;
    // console.log('user._id', user._id)

    // jsonwebtoken을 이용해서 token을 생성하기 
    var token = jwt.sign(user._id.toHexString(), 'secretToken')
    // user._id + 'secretToken' = token 
    // -> 
    // 'secretToken' -> user._id

    user.token = token
    user.save(function (err, user) {
        if (err) return cb(err)
        cb(null, user)
    })
}

userSchema.statics.findByToken = function(token, cb) {
    var user = this;
    // user._id + ''  = token
    //토큰을 decode 한다. 
    jwt.verify(token, 'secretToken', function (err, decoded) {
        //유저 아이디를 이용해서 유저를 찾은 다음에 
        //클라이언트에서 가져온 token과 DB에 보관된 토큰이 일치하는지 확인
        user.findOne({ "_id": decoded, "token": token }, function (err, user) {
            if (err) return cb(err);
            cb(null, user)
        })
    })
}



const User =mongoose.model('User',userSchema)

module.exports = { User };