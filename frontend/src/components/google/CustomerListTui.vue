<template>
  <div>
    <grid
        v-if="data.length > 0"
        ref="ref"
        :data="data"
        :columns="columns">
    </grid>

  </div>
</template>

<script>
  import 'tui-grid/dist/tui-grid.css';
  import { Grid } from '@toast-ui/vue-grid';

  export default {
    components: {
        grid: Grid
    },
    data() {
      return {
        data : [],
        columns: [
          {header: 'id', name: 'id'},
          {header: 'descriptiveName', name: 'descriptiveName'},
          {header: 'currencyCode', name: 'currencyCode'},
          {header: 'hidden', name: 'hidden'},
          {header: 'level', name: 'level'},
          {header: 'manager', name: 'manager'},
          {header: 'timeZone', name: 'timeZone'},
          {header: 'testAccount', name: 'testAccount'}
        ]
      }
    },
    created() {
      this.gridProps = {

      }
    },
    mounted() {
      this.getCampaignList();
    },
    methods : {
      getCampaignList() {
        this.$axios.get('/api/google/customer/sub-customers')
            .then(res => {
              // console.log(res.data);
              this.data = res.data;
              // this.$refs.grid.invoke('resetData', res.data);
            }).catch(error => {
          console.log(error);
        })
      }
    }
  }
</script>