const app = Vue.createApp({
  data() {
    return {
      datos: [],
      accounts: [],
      destinyAccount:"",
      amount:"",
      loans:[],
      loanId:"",
      payments:"",  
      interests:[],
      
      
    
      // alert:this.alertAmount()
    }
  },
  created() {

      this.loadData();
      this.loadLoans();

  },


  methods: {

    loadLoans(){
      axios.get("/api/loans" )
        .then(response => {

          this.loans= response.data
          this.principalId=this.loans[0].id
          this.interests=this.loans.map(prestamo=>prestamo.interests)
          console.log(this.loans[3].maxAmount)

        })

    },  

    loadData() {
      axios.get("/api/clients/current")
        .then(response => {
          this.datos = response
          this.account =this.datos.data
          this.accounts = this.account.accounts

        
        
          // this.ids = this.account.id

        })
    },


    makeAtransaction() {
      axios.post('/api/loans',{'id':this.loanId,'amount':this.amount,'payments':this.payments,'numberAccount':this.destinyAccount})
        .then(response => window.location.href = `http://localhost:8080/web/accounts.html`)
        .catch(function(error){
         Swal.fire(error.response.data)
    })
    },
    modificarSaldo(saldo) {
      return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(saldo);
    },
    // suma(){
    //     return 
    // }


  },
  computed: {

    // filterLoanByName(){

    //   let loanSelect=this.loans.filter(loan=>this.loanId==loan.id)
    //   this.selectedLoan.id=loanSelect[0].id
    //   this.selectedLoan.interests=loanSelect[0].interests
    //   this.selectedLoan.amount=loanSelect[0].amount
    //   this.selectedLoan.name=loanSelect[0].name

    //   return this.selectedLoan

    // },

    alertAmount(){

       if(this.amount>this.loans[this.loans.indexOf(this.loans.id==this.loanId)].maxAmount){
        Swal.fire('the amount can not be bigger than the limit')
       }
    }

    
  },

})
app.mount('#app')


//loanId->x-principalId-y*k = posicion loan[posicion]