<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bankbantuan/listsppbantuan.js"></script>    

<script type="text/javascript">
    $(document).ready(function() {
        
        tampil();
    });
    
    function tampil() {
        grid();
    }
    
    function ambilketerangan(id) {
        $('#idSpp', window.parent.document).val($("#idspp" + id).val()).change();
        $('#noSpp', window.parent.document).val($("#nospp" + id).val()).change();
        $('#ketSkpd', window.parent.document).val($("#skpd" + id).val()).change();
        $('#ketBendahara', window.parent.document).val($("#bendahara" + id).val()).change();
        $('#noRek', window.parent.document).val($("#norek" + id).val()).change();
        $('#namaPenerima', window.parent.document).val($("#penerima" + id).val()).change();
        $('#alamatPenerima', window.parent.document).val($("#alamat" + id).val()).change();
        $('#namaOrg', window.parent.document).val($("#namaorg" + id).val()).change();
        $('#uraian', window.parent.document).val($("#uraian" + id).val()).change();
        $('#nilaiSpp', window.parent.document).val($("#nilaispp" + id).val()).change();
        
        parent.$.fancybox.close();
    }
   
</script>

<form:form   method="post" commandName="refkegiatan"  id="refkegiatan"   action="" class="form-horizontal">
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar SPP BTL Bantuan</div>
    </div>
    
    <div class="portlet-body">
        
        <table  width="100%" >

        <tr><td colspan="5"  ></td></tr>
            
 
    </table>
         
        <div class="form-horizontal" >

            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text"  name="tahun"  id="tahun" value="${tahunAnggaran}" size="4"  />
                        
                    </div>
                </div>
            </div>
                    
            <!-- 
            <div class="form-group">
                <label class="col-md-4 control-label">Nama Bank : </label>
                <div class="col-md-6">
                    <div class="input-group">
                        <input type="text"  name="nama"  id="nama"  class="form-control " size="50" onkeyup="if (event.keyCode == 13)
                                tampil()" />
                    </div>
                </div>
            </div>
            -->
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
        <table id="banktable" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                <tr>
                    <th>No</th>
                    <th>No SPP</th>
                    <th>SKPD Koordinator</th>
                    <th>Uraian SPP</th>
                    <th>Nilai SPP</th>
                    <th>Pilih</th>
                </tr>
            </thead>
            <tbody  >
            </tbody>
        </table> 
    </div>    
</div>
</form:form>