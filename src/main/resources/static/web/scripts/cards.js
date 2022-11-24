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
      cards: [],
      creditCards: [],
      debitCards: [],
      ventana: false,
      date: new Date(),
      // dateActual:this.datetoJSON()


    }
  },
  created() {
    this.loadData();


  },


  methods: {

    loadData() {
      axios.get("/api/clients/current")
        .then(response => {
          this.datos = response
          this.client = this.datos.data
          this.account = this.client.accounts
          this.loans = this.client.clientLoans
          this.cards = this.client.card
          this.creditCards = this.cards.filter(card => card.type == "CREDIT")
          this.debitCards = this.cards.filter(card => card.type == "DEBIT")
        })
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

    formatoFecha(date) {
      
      
      let day = `${(date.getDate())}`.padStart(2, '0');
      let month = `${(date.getMonth() + 1)}`.padStart(2, '0');
      let year = date.getFullYear();

      let formato=`${year}-${month}-${day}`

      return formato
    }

  },
  computed: {

  },

})
app.mount('#app')