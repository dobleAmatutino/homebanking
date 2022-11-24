const  app = Vue.createApp({
  data() {
    return {
      clients:[],
      datos:{},
      name:'',
      lastName:'',
      email:'',
      cliente:{}
    }
  },
  created(){

    this.localData();
    console.log(this.clients)
  },
  methods:{
   
    localData(){
      axios.get("/clients")
       .then(response=>{
        
        this.datos=response
        this.clients=response.data._embedded.clients
        console.log(this.clients)
       })
       
    },
    

  },

  computed:{

    creacionDecliente(){
      objeto={}

      let firstName=this.name
      let lastName=this.lastName
      let email=this.email
    
      objeto={
        firstName:this.name,
        lastName:this.lastName,
        email:this.email
      }
      
      return this.clients
    },
    
   
    
  },

   
})
app.mount('#app')





