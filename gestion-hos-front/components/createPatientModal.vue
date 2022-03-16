<template>
  <div class="modal-overlay">
    <div class="modale d-flex flex-column justify-content-around">
      <h6 class="mt-4">Ajouter un patient</h6>
      <!-- Form -->
      <form class="p-4">
        <div class="form-row">
            <div class="form-group col-md-6">
            <label for="inputEmail4">Nom</label>
            <input type="text" class="form-control" v-model="user.lastName" placeholder="Nom">
            </div>
            <div class="form-group col-md-6">
            <label for="inputPassword4">Prénom</label>
            <input type="text" class="form-control" v-model="user.firstName" placeholder="Prénom">
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputAddress">Date de naissance</label>
                <input type="date" class="form-control" v-model="user.birthDate">
            </div>
            <div class="form-group col-md-6">
                <label for="inputAddress">N°Sécurité sociale</label>
                <input type="text" class="form-control" v-model="user.numSecu">
            </div>
        </div>
        
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputAddress">Téléphone</label>
                <input type="text" class="form-control" v-model="user.phone" placeholder="0600000000">
            </div>
            
            <div class="form-group col-md-6">
                <label for="inputAddress">Email</label>
                <input type="mail" class="form-control" v-model="user.email" placeholder="adresse@mail.fr">
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-12">
                <label for="inputAddress">Mot de passe</label>
                <input type="password" class="form-control" v-model="user.password" placeholder="Mot de passe">
            </div>
        </div>

        <div class="form-group">
            <label for="inputAddress2">Adresse</label>
            <input type="text" class="form-control" v-model="user.address" placeholder="Numero d'appartement, rue, etc..">
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
            <label for="inputCity">Ville</label>
            <input type="text" class="form-control" v-model="user.city" placeholder="Ville">
            </div>
            <div class="form-group col-md-6">
            <label for="inputState">Code postal</label>
            <input type="text" class="form-control" v-model="user.zipcode" placeholder="Code postal">
            </div>
        </div>
        
        <div class="form-row">
            <div class="form-group col">
            <label for="roleSelect">Role</label>
            <select class="form-control" id="roleSelect" v-model="selectedRoleId" v-on:change="getSelectedRole()">
              <option v-for="role in roles" :key="role.id" v-bind:value="role.id">{{role.name}}</option>
            </select>
            </div>
        </div>

        </form>
      <!-- End Form -->
      <div class="row d-flex justify-content-around pb-4">
        <button class="cancel" @click="$emit('close-modal')">Annuler</button>
        <button class="confirm" @click="createUser()">Ajouter</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    data: function(){
        return {
          selectedRoleId: '',
          roles: [],
          user : {
              "firstName": "",
              "lastName": "",
              "birthDate": "",
              "email": "",
              "phone": "",
              "password": "",
              "numSecu":"",
              "address":"",
              "city":"",
              "zipcode":"",
              "role":""
          }
        }
    },
    methods: {
        validateForm(){
            // this.user.firstName = 'john';
            // this.user.lastName = 'donny';
            // this.user.birthDate = '1991-10-10';
            // this.user.email = 'johndonny@gmail.com';
            // this.user.password = '123+++';
            return true;
        },
        createUser(){
            if(this.validateForm()){
            this.user.role = this.getSelectedRole();
            this.$axios.post("http://localhost:8080/api/v1/user", this.user)
                .then((res) => {
                    this.$emit('close-modal');
                    this.$parent.getpatients();
                })
                .catch((err) => {
                    console.log('Erreur création patient '+err);
                });
            }
        },
        fetchRoles(){
          this.$axios.$get('http://localhost:8080/api/v1/role/')
          .then((response) => {
            this.roles = response;
          })
          .catch((err) => {
            console.log('Error while fetching patients'+err);
          });
        },
        getSelectedRole(){
          return this.roles.filter(role => {
              return role.id == this.selectedRoleId
            })[0]
        }
    },
    mounted: function(){
      this.fetchRoles();
    }
}
</script>
<style scoped>
.form-group {
    margin-bottom: 5px !important;
}
.modal-overlay {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  display: flex;
  justify-content: center;
  background-color: #555555da;
}

.modale {
  display: block;
  text-align: center;
  background-color: white;
  max-height: 1000px;
  width: 60%;
  max-width: 600px;
  margin: auto;
  padding: 20px 0;
  border-radius: 10px;
}

.close-img {
  width: 25px;
}

.check {
  width: 150px;
}

h6 {
  font-weight: 500;
  font-size: 28px;
  margin: 20px 0;
}

p {
  font-size: 16px;
  margin: 20px 0;
}

button {
  background-color: #ac003e;
  width: 150px;
  height: 40px;
  color: white;
  font-size: 14px;
  border-radius: 16px;
}

.confirm:hover {
  background-color: #8f0034;
}

.cancel {
  background-color: #fff !important;
  color: #ac003e !important;
  border: 1px solid;
  border-color: #ac003e !important;
}

.cancel:hover {
  background-color: rgb(255, 238, 238) !important;
}
</style>
