export default function ({ store, redirect, $axios }){
    // If the user is not logged in
    if (store.state.hello.token == ""){
        return redirect('/login');
    }else{
        $axios.defaults.headers.common = {'Authorization': `Bearer ${store.state.hello.token}`}
        // $axios.defaults.headers.common['Authorization'] = store.state.hello.token;
    }
}