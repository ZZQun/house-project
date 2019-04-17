<template>
    <div class="dialog3">
        <el-dialog 
            :title="dialog3.title" 
            :visible.sync="dialog3.show"
            :close-on-click-modal='false'
            :close-on-press-escape='false'
            :modal-append-to-body="false"
            :before-close='closeDialog'
            width="30%">
            <div class="form">
                <el-form ref="form" :model="formData" label-width="120px" style="margin:10px;width:auto;">
                    <el-form-item label="详情：" prop="detail" :rows="4">
                        <span>{{this.formData.detail}}</span>
                    </el-form-item>
                    
                </el-form>
            </div>
        </el-dialog>
    </div>
</template>

<script>

export default {
    name: 'userview-dialog',
    props: {
        dialog3:Object,
        formData:Object
    },
    data () {
        return {         
            
        }
    },
    created () {
        
    },
    methods: {
        
        closeDialog(done){ 
            if (this.$refs['form'] !== undefined) {
                        this.$refs['form'].resetFields();
                    } 
            done();
        },
        
    }
};
</script>


<style scoed>

</style>

