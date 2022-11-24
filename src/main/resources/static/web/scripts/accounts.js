const app = Vue.createApp({
  data() {
    return {
      datos: [],
      client: [],
      account: [],
      transaction: [],
      fecha: "",
      hoy: "",

      loans: [],

    }
  },
  created() {
    this.loadData();
    this.modalsito();

  },


  methods: {

    modalsito(){
    return Swal.fire({title:'¡Hi,mr. corrector!', 
      text:'We are working some details of the aplication, and page styles'})
    },

    loadData() {
      axios.get("/api/clients/current")
        .then(response => {
          this.datos = response
          this.client = this.datos.data
          this.account = this.client.accounts
          this.loans = this.client.clientLoans

          this.account.sort((a,b)=>b.id-a.id)



      })
    },

    addNewAccount() {
      
      return axios.post('/api/clients/current/accounts')
        .then(response => window.location.href = "http://localhost:8080/web/accounts.html")
    },

    fechaActual() {
      this.a = new Date()
      this.hoy = a.now()

    },

    modificarSaldo(saldo) {
      return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(saldo);
    },
    // suma(){
    //     return 1+1
    // }


  },
  computed: {

  },

})
app.mount('#app')