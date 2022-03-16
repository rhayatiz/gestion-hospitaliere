<template>
  <div>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <div class="flex flex-row">
      <vertical-navbar />
      <main class="w-full">
        <horizontal-navbar />
            <div class="wrapper">
                <div class="py-2 col-12 border-bottom">
                    <nuxt-link class="h4 font-weight-light" :to="{ name: 'patients' }">
                        <i class="fa-solid fa-arrow-left"></i><span class="ml-2">Patients</span>
                    </nuxt-link>
                </div>

                <div class="container border rounded mt-3">
                    <span class="h4">Fiche patient</span>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label class="form-label" for="">Nom</label>
                            <input class="form-control" v-model="this.user.firstName" readonly>
                        </div>
                    
                        <div class="form-group col-md-6">
                            <label class="form-label" for="">Prénom</label>
                            <input class="form-control" v-model="this.user.lastName"  readonly>
                        </div>
                    
                        <div class="form-group col-md-6">
                            <label class="form-label" for="">Date de naissance</label>
                            <input class="form-control" v-model="this.userBirthDate" readonly>
                        </div>

                        <div class="form-group col-md-6">
                            <label class="form-label" for="">Email</label>
                            <input class="form-control" v-model="this.user.email" readonly>
                        </div>
                    </div>
                </div>

                <div class="container border rounded mt-4">
                    <div class="row">
                        <span class="h4">Liste des dossiers</span>
                        <button type="button" class="btn btn-sm btn-info ml-auto my-1 mr-1"  @click="showModal = true">Créer un dossier</button>
                    </div>
                    <table class="table table-sm">
                        <thead>
                        <tr>
                            <th scope="col">Dossier</th>
                            <th scope="col">Date de création</th>
                            <th scope="col">Nom</th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-if="dossiers.length == 0">
                            <td colspan="4" class="text-center">Aucun dossier</td>
                        </tr>
                        <tr v-else v-for="dossier in dossiers" :key="dossier.id">
                            <th scope="row">{{dossier.id}}</th>
                            <td>{{dossier.createdAt}}</td>
                            <td>{{dossier.name}}</td>
                            <td>
                            <a class="d-flex flex-column btn-sm btn">
                                <i class="fas fa-eye"></i>
                                <span style="font-size:11px" class="font-weight-light">Voir dossier</span>
                            </a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
  
            </div>
      </main>
      <SavedModal v-show="showModal" @close-modal="showModal = false"/>
    </div>
  </div>
</template>

<script>
import SavedModal from '~/components/createPatientRecordModal.vue'

export default {
    components: { SavedModal },
    middleware: 'authenticated',
    data(){
        return {
            showModal: false,
            dossiers: [],
            user: {
                firstName: '',
                lastName: '',
                birthDate: [],
                email: ''
            }
        }
    },
    middleware: 'authenticated',
    name: 'patients',
    methods: {
        fetchUser(){
            let userId = this.$route.query.id; 
            this.$axios.get('http://localhost:8080/api/v1/user/find/'+userId)
                .then((res) => {
                    console.log(res.data);
                    this.user = res.data;
                })
                .catch((err) => {
                    console.log(err);
                });
        }
    },
    mounted: function() {
        this.fetchUser();
    },
    computed: {
        userBirthDate: function(){
            return this.user.birthDate.reverse().join('/');
        }
    }

}
</script>

<style>
 span.h4 {
     background-color: white;
     position: relative;
     top: -15px;
     padding: 0 10px;
 }
 
 .form-label {
     font-weight: 300;
 }
</style>