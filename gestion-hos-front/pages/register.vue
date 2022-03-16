<template>
    <div class="flex justify-center items-center h-screen">

        <div class="rounded-xl px-10 py-8
                border border-light-blue-800 shadow-md
                w-80">
        <div class="text-blue-500 text-2xl text-center font-light">Inscription</div>

        <form id="registerForm" method="POST" @submit.prevent="submitHandler()"  action="#">
            <div class="relative mt-10">
                <label class="absolute -top-3 left-2 bg-white px-1 font-light text-gray-800" for="firstName">Prénom</label>
                <input v-bind:class="[errors.firstName ? 'error-input' : 'blue-input']" v-model="firstName" type="text" placeholder="firstName" name="firstName" id="firstName"/>
            </div>

            <div class="relative mt-10">
                <label class="absolute -top-3 left-2 bg-white px-1 font-light text-gray-800" for="lastName">Nom</label>
                <input v-bind:class="[errors.lastName ? 'error-input' : 'blue-input']" v-model="lastName" type="text" placeholder="lastName" name="lastName" id="lastName"/>
            </div>

            <div class="relative mt-10">
                <label class="absolute -top-3 left-2 bg-white px-1 font-light text-gray-800" for="email">Email</label>
                <input v-bind:class="[errors.email ? 'error-input' : 'blue-input']" v-model="email" type="text" placeholder="email" name="email" id="email" />
            </div>

            <div class="relative mt-6">
                <label class="absolute -top-3 left-2 bg-white px-1 font-light" for="date">Date de naissance</label>
                <input v-bind:class="[errors.birthDate ? 'error-input' : 'blue-input']" v-model="birthDate" type="date" name="date" id="date"/>
            </div>

            <div class="relative mt-6">
                <label class="absolute -top-3 left-2 bg-white px-1 font-light" for="password">Mot de passe</label>
                <input v-bind:class="[errors.password ? 'error-input' : 'blue-input']" type="password" v-model="password" placeholder="password" name="password" id="password" th:field="*{password}"/>
            </div>

            <div class="relative mt-6">
                <label class="absolute -top-3 left-2 bg-white px-1 font-light" for="password">Confirmation</label>
                <input v-bind:class="[errors.passwordConfirm ? 'error-input' : 'blue-input']" type="password" v-model="passwordConfirm" placeholder="passwordConfirm" name="passwordConfirm" id="passwordConfirm"/>
            </div>

            <ul v-if="errorMessages.length > 0" class="list-disc mt-3 py-1 font-light bg-red-100 text-red-800 rounded-md">
                <li v-for="error in errorMessages" :key="error" v-text="error" class="px-2"></li>
            </ul>

            <button class="text-white bg-blue-500 hover:bg-blue-600 mt-5 w-full font-light py-1 rounded-lg" type="input">Inscription</button>

            <div class="text-center mt-2"><a class="font-light text-blue-400 hover:text-blue-500 underline" href="reset-password">Reset password</a></div>
            <div class="text-center mt-2 font-light">Déjà inscrit?<a class="text-blue-400 hover:text-blue-500 underline" href="/login">Connexion</a></div>

        </form>
    </div>

</div>
</template>

<script>
export default {
    data(){
        return {
            errors: {
                firstName:false,
                lastName:false,
                email:false,
                birthDate:false,
                password:false,
                passwordConfirm:false,
            },
            errorMessages: [],
            firstName: "",
            lastName: "",
            email: "",
            birthDate: "",
            password: "",
            passwordConfirm: "",
        }
    },
    methods:{
        submitHandler() {
        this.errorMessages = [];
        // firstName
        if (this.firstName == "") {
          this.errors.firstName = true;
        } else {
          this.errors.firstName = false;
        }
        // lastName
        if (this.lastName == "") {
          this.errors.lastName = true;
        } else {
          this.errors.lastName = false;
        }
        // email
        if (this.email == "") {
          this.errors.email = true;
        } else {
          this.errors.email = false;
        }
        // birthDate
        if (this.birthDate == "") {
          this.errors.birthDate = true;
        } else {
          this.errors.birthDate = false;
        }
        // password
        if (this.password == "") {
          this.errors.password = true;
        } else {
          this.errors.password = false;
        }
        // passwordConfirm
        if (this.passwordConfirm == "") {
          this.errors.passwordConfirm = true;
        } else {
          this.errors.passwordConfirm = false;
        }

        if (this.password != "" && (this.password != this.passwordConfirm)) {
          this.errorMessages.push('Les deux mots de passe ne correspondent pas');
        }

        if(!Object.values(this.errors).every(x => x == false)){
          this.errorMessages.push('Veuillez renseigner tous les champs');
        }

        if(this.errorMessages.length == 0 && Object.values(this.errors).every(x => x == false)){
            this.sendRegister();
        }
      },

      sendRegister(){
          this.$axios.$post('/api/v1/user', {
              	"firstName": this.firstName,
                "lastName": this.lastName,
                "birthDate": this.birthDate,
                "email": this.email,
                "password": this.password
          })
          .then((res) => console.log(res))
          .catch((err) => console.log(err));
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