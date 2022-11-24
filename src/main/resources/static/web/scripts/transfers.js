const app = Vue.createApp({
  data() {
    return {
      originAccountData:[],
      datos: [],
      accounts: [],
      transaction: [],
      movements: '',
      accounts: [],
      id: new URLSearchParams(location.search).get('id'),
      // ids: "",
      destiny: "",
      originAccount: "",
      destinyAccount: "",
      amount:"",
      description: "",
      accountCurrenting: [],
      dOriginAccount: "",
      balance:"",
    }
  },
  created() {

    this.loadData();
    this.originData();

  },


  methods: {

    originData() {
      axios.get("/api/accounts/" + this.id)
        .then(response => {

          this.originAccountData = response
          this.dOriginAccount = this.originAccountData.data.number

        })
    },

    loadData() {
      axios.get("/api/clients/current")
        .then(response => {
          this.datos = response
          this.account = this.datos.data
          this.accounts = this.account.accounts
          this.transaction.sort((a, b) => b.id - a.id)
          // this.ids = this.account.id

        }) 
    },

    makeAtransaction() {
      Swal.fire({
        icon:'question',
        icon:'alert',
        title:'Are you sure do you want to make a transfer?',
        showDenyButton:true,
        confirmButtonText:'confirm',
        denyButtonText:' aint',
        confirmButtonColor: 'blueviolet',
      
        
      })

        .then((result)=>{if (result.isConfirmed){
            return  axios.post('/api/transactions', `amount=${this.amount}&description=${this.description}&originAccount=${this.dOriginAccount}&destinyAccount=${this.destinyAccount}`)
            .then(response => window.location.href = `http://localhost:8080/web/account.html?id=${this.id}`)
        
        .catch(function(error){   
                 
        Swal.fire(error.response.data)

        })

        }}
      )
    },

    modificarSaldo(saldo) {
      return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(saldo);
    },
    // suma(){
    //     return 
    // }


  },
  computed: {


  },

})
app.mount('#app')