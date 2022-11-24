
     
const  app = Vue.createApp({
  data() {
    return {
      clients:[],
      datos:[],
      firstName:'',
      lastName:'',
      email:'',
      cliente:{},
      password:'',
      alerta:false,
      nuevoUsuario:{},
      paymentArray:[],
      payments:0,
      maxAmount:"",
      interests:1,
      nameLoan:"",
      loanList:"",
    }
  },
  created(){

    this.loadData();
    // console.log(this.clients)
  //   this.postClient();
    
  },
  methods:{

    // paymentPussh(array){
    //   return array.push(this.payments)
    // },

   
    loadData(){
      axios.get("/api/loans")
       .then(response=>{
        
        this.datos=response 
        this.loanList=this.datos.data

        
        // this.clients=response.data._embedded.clients
        // console.log(this.clients)
       })
      },
       
    
    createLoan() {
      this.paymentArray.push(this.payments)
       return axios.post('/api/createLoan', `name=${this.nameLoan}&payments=${this.paymentArray}&maxAmount=${this.maxAmount}&interests=${this.interests}`)
    .then(this.loadData())
    },
      
  },
  computed:{

 
  },

   
})
app.mount('#app')



  
  
  
  