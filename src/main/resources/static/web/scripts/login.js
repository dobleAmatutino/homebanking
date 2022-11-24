const app = Vue.createApp({
  data() {
    return {
      datos: [],
      client: [],
      account: [],
      transaction: [],
      email: "",
      password: "",
      fecha: "",
      hoy: "",

      loans: [],

    }
  },
  created() {


  },


  methods: {




    logIn() {
      let emailLog = this.email
      let passwordLog = this.password
      return axios.post('/api/login', `email=${emailLog}&password=${passwordLog}`)
        .then(response => window.location.href = "/web/accounts.html")
        .catch(function () {


          return Swal.fire('invalid credentials')
        }

        )

    },

    logOut() {
      return axios.post('/api/logout')
        .then(response => window.location.href = "/web/login.html")
    },

    addClient() {

      nuevoUsuario = {
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email.toLowerCase()
      }

      this.postClient(nuevoUsuario)
      location.reload()

    },

    postClient(obj) {
      axios.post("/clients", obj)
        .then(this.loadData())

    },


  },



})
app.mount('#app')