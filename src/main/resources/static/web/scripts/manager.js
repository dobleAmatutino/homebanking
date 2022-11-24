
     
const  app = Vue.createApp({
  data() {
    return {
      clients:[],
      datos:{},
      firstName:'',
      lastName:'',
      email:'',
      cliente:{},
      password:'',
      alerta:false,
      nuevoUsuario:{},
    }
  },
  created(){

    this.loadData();
    console.log(this.clients)
  //   this.postClient();
    
  },
  methods:{
   
    loadData(){
      axios.get("/api/clients")
       .then(response=>{
        
        this.datos=response 
        this.clients=this.datos.data
        // this.clients=response.data._embedded.clients
        // console.log(this.clients)
       })
      },
       
    
    addClient() {


      let firstName = this.firstName
      let email = this.email.toLowerCase()
      let password = this.password
      let lastName = this.lastName

       return axios.post('/api/clients', `firstName=${firstName}&lastName=${lastName}&email=${email}&password=${password}`)
   .then(this.loadData())
    },
      
  },
  computed:{

 
  },

   
})
app.mount('#app')



