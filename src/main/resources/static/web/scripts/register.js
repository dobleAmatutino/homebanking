const app = Vue.createApp({
  data() {
    return {
      firstName: "",
      lastName: "",
      email: "",
      password: "",
      fecha: "",
      hoy: "",

    }
  },
  created() {


  },


  methods: {


    addClient() {


      let firstName = this.firstName
      let email = this.email.toLowerCase()
      let password = this.password
      let lastName = this.lastName

       return axios.post('/api/clients', `firstName=${firstName}&lastName=${lastName}&email=${email}&password=${password}`)
        .then(response => axios.post('/api/login', `email=${email}&password=${password}`)//para iniciar sesion con los datos recien guardados arriba
        .then(response => window.location.href = "http://localhost:8080/web/accounts.html"))//vas a mostrar los datos de la persona que recien guardaste
        .catch(function(error){
          console.log(error.response.data)
          if(error.response.data=="missing firstName"){
            return Swal.fire('First Name field is missing')
          }
          if(error.response.data=="missing lastName"){
            return Swal.fire({title:'Last Name field is missing'})
          }
          if(error.response.data=="missing email"){
            return Swal.fire({title:'Email field is missing'})
          }
          if(error.response.data=="missing password"){
            return Swal.fire({title:'Password field is missing'})
          }
          if(error.response.data=="Name already in use"){

            return Swal.fire({title:'Name is being using'})
          }
          if(error.response.data=="the mails contains an @"){

            return Swal.fire({title:'the emails should contains @'})
          }
        })
    },



  },



})
app.mount('#app')