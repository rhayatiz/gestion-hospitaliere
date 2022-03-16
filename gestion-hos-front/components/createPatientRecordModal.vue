<template>
  <div class="modal-overlay">
    <div class="modale d-flex flex-column justify-content-around">
      <h6 class="mt-4">Créer un dossier</h6>
      <!-- Form -->
      <form class="p-4">
        <div class="form-row">
            <div class="form-group col">
              <label for="inputEmail4">Nom</label>
              <input type="text" class="form-control" v-model="record.name" placeholder="Nom">
            </div>
        </div>
        </form>
      <!-- End Form -->
      <div class="row d-flex justify-content-around pb-4">
        <button class="cancel" @click="$emit('close-modal')">Annuler</button>
        <button class="confirm" @click="createRecord()">Créer</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    data: function(){
        return {
            userId: "",
            record: {
                "name": "",
            }
        }
    },
    methods: {
        validateForm(){
            this.userId = this.$parent.user.id;
            // this.user.firstName = 'john';
            // this.user.lastName = 'donny';
            // this.user.birthDate = '1991-10-10';
            // this.user.email = 'johndonny@gmail.com';
            // this.user.password = '123+++';
            return true;
        },
        createRecord(){
            if(this.validateForm()){
                console.log('creating medical record');
                console.log(this.record);
                let data = {
                  userId: this.userId,
                  name: this.record.name
                }
                this.$axios.post('http://localhost:8080/api/v1/medicalRecord/', data)
                  .then((res) => {
                      this.$emit('close-modal');
                      this.$parent.fetchUser();
                  })
                  .catch((err) => {
                      console.log('Erreur création dossier '+err);
                  });
            }
        },
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
