const  app = Vue.createApp({
    data() {
      return {
        datos:[],
        account:[],
        transaction:[],
        movements:'',
        id:new URLSearchParams(location.search).get('id')
      }
    },
      created(){

        this.loadData();
       
        
    },
    
    
    methods:{
     
        loadData(){
          axios.get("/api/accounts/"+this.id)
           .then(response=>{
            this.datos=response
            this.account=this.datos.data
            this.transaction=this.account.transactions
            this.transaction.sort((a,b)=>b.id-a.id)
            

           })},
        

        modificarSaldo(saldo){
            return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(saldo);
        },
        // suma(){
        //     return 1+1
        // }
           
      
    },
    computed:{
      

    },

})
app.mount('#app')