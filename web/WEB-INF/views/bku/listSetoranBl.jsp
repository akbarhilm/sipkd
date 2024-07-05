<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bku/listSetoranBl.js"></script>    

<script type="text/javascript">
    $(document).ready(function() {
       // bebanval = window.localStorage.getItem("ketBeban");
       // nobkuval = window.localStorage.getItem("ketNoBku");
        
        bebanval = $('#beban', window.parent.document).val();
        nobkuval = $('#noBKU', window.parent.document).val();
        
        tampil();
    });
    
    function tampil() {
        grid();
    }
    
    function ambilskpd(id) {
        $('#pengeluaran', window.parent.document).val($("#nilai" + id).val()).change();
        $('#idspd', window.parent.document).val($("#idspd" + id).val()).change();
        $('#idSpd', window.parent.document).val($("#idspd" + id).val()).change();
        $('#noBuktiDok', window.parent.document).val($("#novalidasi" + id).val()).change();
        $('#uraian', window.parent.document).val($("#uraian" + id).val()).change();
        $('#noBukti', window.parent.document).val($("#novalidasi" + id).val()).change();
        $('#tglPosting', window.parent.document).val($("#tglvalidasi" + id).val()).change();
        $('#tglDok', window.parent.document).val($("#tgldok" + id).val()).change();
        $('#nosetor', window.parent.document).val($("#nosetor" + id).val()).change(); // jangan dihapus
        
        parent.$.fancybox.close();
    }
</script>

<form:form   method="post" commandName="refkegiatan"  id="refkegiatan"   action="" class="form-horizontal">
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Setoran</div>
    </div>
    
    <div class="portlet-body">
        
        <table  width="100%" >

        <tr><td colspan="5"  ></td></tr>
            
 
    </table>
         
        <div class="form-horizontal" >

            <div class="form-group">
                 <br></br>
                <label class="col-md-4 control-label">No STS :</label>
                <div class="col-md-6">
                    <div class="input-group">
                        <form:hidden path="tahun" id="tahun" value="${tahunAnggaran}" />
                        <form:hidden path="skpd.idSkpd" id='idskpd' value="${skpd.idSkpd}"  />
                        
                        <input type="text"  name="kode"  id="kode"  class="form-control " size="30" onkeyup="if (event.keyCode == 13)
                                tampil()" />
                    </div>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-md-4 control-label">No Validasi : </label>
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
                    <th>No Setor</th>
                    <th>Tgl Validasi</th>
                    <th>No STS</th>
                    <th>No Validasi</th>
                    <th>Nilai Setor</th>
                    <th>Pilih</th>
                </tr>
            </thead>
            <tbody  >
            </tbody>
        </table> 
    </div>    
</div>
</form:form>