<template>
      <div class="flex justify-center items-center h-screen">

        <div class="rounded-xl px-10 py-8
                border border-light-blue-800 shadow-md
                w-80">
            <div class="text-blue-500 text-2xl text-center font-light">Connexion</div>

            <form id="loginForm" @submit.prevent="submitHandler()" method="POST" action="#" class="w-100">
                <div class="relative mt-10">
                    <label class="absolute -top-3 left-2 bg-white px-1 font-light text-gray-800" for="email">Email</label>
                    <input v-bind:class="[emailError ? 'error-input' : 'blue-input']" v-model="email" type="text" placeholder="Email" name="email" id="email" />
                </div>

                <div class="relative mt-6">
                    <label class="absolute -top-3 left-2 bg-white px-1 font-light" for="password">Password</label>
                    <input v-bind:class="[passwordError ? 'error-input' : 'blue-input']" v-model="password" type="password" placeholder="password" name="password" id="password" />
                </div>

                <div v-if="errors.length > 0" class="mt-3 py-1 font-light bg-red-100 text-red-800 rounded-md">
                    <div v-for="error in errors" :key="error" v-text="error" class="px-2"></div>
                </div>

                <button class="text-white bg-blue-500 hover:bg-blue-600 mt-5 w-full font-light py-1 rounded-lg" type="input">Connexion</button>

                <button type="button" class="border text-blue-500 bg-white border-blue-500 hover:bg-blue-500 hover:text-white mt-2 w-full font-light py-1 rounded-lg">
                    <a class="px-5" href="/register">Inscription</a>
                </button>

                <strike><div class="text-center mt-2"><a class="font-light text-blue-400 hover:text-blue-500 underline" href="/reset-password">Reinitialiser mot de passe</a></div></strike>

            </form>
        </div>

    </div>
</template>

<script>
export default {
    data: function(){
        return {
            errors: [],
            email: "",
            password: "",
            emailError:false,
            passwordError:false
        }
    },
    name: 'LoginPage',
    methods: {
      submitHandler() {
        if (this.email == "") {
          this.emailError = true;
        } else {
          this.emailError = false;
        }
        if (this.password == "") {
          this.passwordError = true;
        } else {
          this.passwordError = false;
        }
        if (this.email == "" || this.password == "") {
          this.errors = [];
          this.errors.push('Veuillez renseigner vos identifiants');
        } else {
          this.errors = [];
          this.postLogin();
        }
      },

      postLogin(){
          this.$axios.$post('http://localhost:8080/api/v1/user/authenticate', {
                "username": this.email,
                "password": this.password
          })
          .then((response) => {
            // settoken
            localStorage.setItem('token', response.token);
            this.$store.dispatch("hello/setToken", response.token);
            this.$router.push('/home');
          })
          .catch((err) => {
            localStorage.removeItem('token');
            // https://www.digitalocean.com/community/tutorials/handling-authentication-in-vue-using-vuex
            // axios.defaults.headers.common['Authorization'] = token
            this.errors.push('Identifiants incorrects');
          });
      }
    }
}
</script>

<style>
@tailwind base;
@tailwind utilities;
@tailwind components;

.blue-input {
    @apply border border-blue-200 focus:border-blue-400 rounded-lg py-2 pl-2 font-light outline-none w-full;
}

.error-input {
    @apply border-2 border-red-200 focus:border-red-400 rounded-lg py-2 pl-2 font-light outline-none w-full;
}

</style>
