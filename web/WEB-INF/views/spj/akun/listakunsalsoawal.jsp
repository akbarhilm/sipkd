<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/akun/akunlistsaldoawal.js"></script>    

<script type="text/javascript">
    $(document).ready(function() {
        //tampil();
    });
    
    function tampil() {
        grid();

    }
    function ambilskpd(id) {
        $('#idakunpop', window.parent.document).val($("#kode" + id).val()).change();
        $('#namaakunpop', window.parent.document).val($("#nama" + id).val()).change();
        $('#idbaspop', window.parent.document).val(id).change();
        
        parent.$.fancybox.close();
    }
</script>

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Akun</div>

    </div>
    <div class="portlet-body">
        <div class="form-horizontal" >
            
            <div class="form-group">
                
                <div class="col-md-offset-3" >
                    <input type="radio" name="rbtn" onclick="cekrb(this.value)" value="1"> 1 - Aset <BR>
                </div>
                <div class="col-md-offset-3" >
                    <input type="radio" name="rbtn" onclick="cekrb(this.value)" value="2"> 2 - Kewajiaban <BR>
                </div>
                <div class="col-md-offset-3" >
                    <input type="radio" name="rbtn" onclick="cekrb(this.value)" value="3"> 3 - Ekuitas <BR>
                </div>
                
            </div>
            
             
            <div class="form-group">
                <label class="col-md-3 control-label">Kode Akun :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="text"  name="kode"  id="kode"  class="form-control " size="30" onkeyup="if (event.keyCode == 13)
                                tampil()" />
                    </div>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Akun : </label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="text"  name="nama"  id="nama"  class="form-control " size="50" onkeyup="if (event.keyCode == 13)
                                tampil()" />
                    </div>
                </div>
            </div>
            
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn dark" onclick='tampil()'>Cari</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="portlet box">
    
    <div >
        <table id="skpdtable" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                <tr>
                    <th>No</th> 
                    <th>Kode</th>
                    <th>Nama Akun</th>
                    <th>Pilih</th>
                </tr>
            </thead>
            <tbody  >
            </tbody>
        </table> 
    </div>    
</div>