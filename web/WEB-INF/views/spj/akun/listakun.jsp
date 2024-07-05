<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/akun/akunlistpopup.js"></script>    

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
        $('#akunketpop', window.parent.document).val($("#namaAkun" + id).val()).change();
        $('#idbaspop', window.parent.document).val(id).change();
        
        parent.$.fancybox.close();
    }
</script>

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Akun</div>

    </div>
    
    <div class="portlet-body">
        
        <table  width="100%" >

        <tr><td colspan="5"  ></td></tr>

        <tr>
           
            <td>
                <input type="radio" name="rbtn" onclick="cekrb(this.value)" value="0"> 0 - Perubahan SAL <BR>
            </td>
            <td>
                <input type="radio" name="rbtn" onclick="cekrb(this.value)" value="1"> 1 - Aset <BR>
            </td>
            <td>
                <input type="radio" name="rbtn" onclick="cekrb(this.value)" value="2"> 2 - Kewajiaban <BR>
            </td>
            <td>
                <input type="radio" name="rbtn" onclick="cekrb(this.value)" value="3"> 3 - Ekuitas <BR>
            </td>
            <td>
                <input type="radio" name="rbtn" onclick="cekrb(this.value)" value="4"> 4 - Pendapatan Daerah <BR>
            </td>
        </tr>

        <tr>
           
            <td>
                <input type="radio" name="rbtn" onclick="cekrb(this.value)" value="5"> 5 - Belanja Daerah <BR>
            </td>
            <td>
                <input type="radio" name="rbtn" onclick="cekrb(this.value)" value="6"> 6 - Pembiayaan Daerah <BR>
            </td>
            <td>
                <input type="radio" name="rbtn" onclick="cekrb(this.value)" value="8"> 8 - Pendapatan Daerah - LO <BR>
            </td>
            <td>
                <input type="radio" name="rbtn" onclick="cekrb(this.value)" value="9"> 9 - Beban <BR>
            </td>
            
        </tr>
 
    </table>
         
        <div class="form-horizontal" >

            <div class="form-group">
                 <br></br>
                <label class="col-md-4 control-label">Kode Akun :</label>
                <div class="col-md-6">
                    <div class="input-group">
                        <input type="text"  name="kode"  id="kode"  class="form-control " size="30" onkeyup="if (event.keyCode == 13)
                                tampil()" />
                    </div>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-md-4 control-label">Nama Akun : </label>
                <div class="col-md-6">
                    <div class="input-group">
                        <input type="text"  name="nama"  id="nama"  class="form-control " size="50" onkeyup="if (event.keyCode == 13)
                                tampil()" />
                    </div>
                </div>
            </div>
            
            <div class="form-actions fluid">
                <div class="col-md-offset-4 col-md-9">
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