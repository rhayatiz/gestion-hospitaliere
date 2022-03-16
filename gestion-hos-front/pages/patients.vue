<template>
  <div>
      
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    <div class="flex flex-row">
      <vertical-navbar />
      <main class="w-full">
        <horizontal-navbar />

        <div class="row mx-3 mt-4 pb-2 border-bottom">
          <span class="h4">
            Liste des patients
          </span>
          <button class="btn btn-sm btn-info ml-auto" @click="showModal = true">
            <i class="fas fa-plus"></i>
            Ajouter
          </button>
        </div>
        <div class="row mx-3 mt-4">
          <table class="table table-sm">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Nom</th>
                <th scope="col">Prénom</th>
                <th scope="col">Date de naissance</th>
                <th scope="col">Role</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="user in users" :key="user.id">
                <th scope="row">{{user.id}}</th>
                <td>{{user.lastName}}</td>
                <td>{{user.firstName}}</td>
                <td>{{user.birthDate[2]+'/'+user.birthDate[1]+'/'+user.birthDate[0]}}</td>
                <td>{{user.role.name}}</td>
                <td>
                  <nuxt-link class="d-flex flex-column btn-sm btn" :to="{ name: 'patient', query: { id : user.id  }}">
                    <i class="fas fa-eye"></i>
                    <span style="font-size:11px" class="font-weight-light">Voir patient</span>
                  </nuxt-link>
                </td>
              </tr>
            </tbody>
          </table>
        </div>

      </main>
      <SavedModal v-show="showModal" @close-modal="showModal = false"/>
    </div>
  </div>
</template>

<script>
import SavedModal from '~/components/createPatientModal.vue'

export default {
    components: { SavedModal },
    middleware: 'authenticated',
    data: function(){
      return {
        showModal: false,
        users: []
      }
    },
    
    methods: {
      async getpatients(){
        this.$axios.$get('http://localhost:8080/api/v1/user/')
        .then((response) => {
          console.log(response[0]);
          this.users = response;
        })
        .catch((err) => {
          console.log('Error while fetching patients');
          console.log(err);
        });
      }
    },

    mounted: function(){
      this.getpatients();
    }
}
</script>

<style>

</style>