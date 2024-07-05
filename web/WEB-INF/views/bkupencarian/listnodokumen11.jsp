<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bkupencarian/listnodokumen.js"></script>    

<script type="text/javascript">
    $(document).ready(function() {
        jenisdok = window.localStorage.getItem("jenisdokval");
        
        var i_id = window.localStorage.getItem("idskpdval");
        $("#idskpd").val(i_id);

        tampil();
    });
    
    function tampil() {
        grid();
    }
    
    function ambilskpd(id) {
        $('#noDokumen', window.parent.document).val($("#nodok" + id).val()).change();
        //$('#idKegpop', window.parent.document).val(id).change();
        
        parent.$.fancybox.close();
    }
</script>

<form:form   method="post" commandName="refkegiatan"  id="refkegiatan"   action="" class="form-horizontal">
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Nomor Dokumen</div>
    </div>
    
    <div class="portlet-body">
        
        <table  width="100%" >
        <tr><td colspan="5"  ></td></tr>
            
    </table>
         
        <div class="form-horizontal" >
            
            <div style="display: none" class="form-group" > <!--  JANGAN DIHAPUS.. NGARUH KE EVENT ENTER PAS PENCARIAN.. ???  -->
                <label class="col-md-4 control-label">No Dokumen :</label>
                <div class="col-md-6">
                    <div class="input-group">
                        
                        <input type="text"  name="nomor4"  id="nomor4"  class="form-control " size="30" onkeyup="if (event.keyCode == 13)
                                tampil()" />
                    </div>
                </div>
            </div>
                 
            <div class="form-group">
                <label class="col-md-4 control-label">No Dokumen : </label>
                <div class="col-md-6">
                    <form:hidden path="tahun" id="tahun" value="${tahunAnggaran}" />
                    <form:hidden path="skpd.idSkpd" id='idskpd' value=""  />
                        
                    <div class="input-group">
                        <input type="text"  name="nomor"  id="nomor"  class="form-control " size="30" onkeyup="if (event.keyCode == 13) grid()" />
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
                    <th>No BKU</th> 
                    <th>Tgl Transaksi</th> 
                    <th>No Filling</th> 
                    <th>Beban</th> 
                    <th>Jenis</th> 
                    <th>Nomor Dokumen</th>
                    <th>Nilai</th>
                    <th>Pilih</th>
                </tr>
            </thead>
            <tbody  >
            </tbody>
        </table> 
    </div>    
</div>
</form:form>