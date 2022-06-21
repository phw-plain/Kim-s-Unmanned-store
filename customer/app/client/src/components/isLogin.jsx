export const isLogin = () => {
    
    if(localStorage.getItem('accessToken') === null){
        window.location.replace('/notLogin')
    }

}

export default isLogin;