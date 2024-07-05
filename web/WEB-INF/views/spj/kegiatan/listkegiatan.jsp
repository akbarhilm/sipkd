<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/kegiatan/kegiatanlistpopup.js"></script>    

<script type="text/javascript">
    $(document).ready(function() {
        var id_value = window.localStorage.getItem("kodeakunval");
        kodeakunval = id_value;
        
        tampil();
    });
    
    function tampil() {
        grid();

    }
    function ambilskpd(id) {
        $('#namakegpop', window.parent.document).val($("#nama" + id).val()).change();
        $('#kodekegpop', window.parent.document).val($("#kode" + id).val()).change();
        $('#idkegpop', window.parent.document).val(id).change();
        
        parent.$.fancybox.close();
    }
</script>

<form:form   method="post" commandName="refkegiatan"  id="refkegiatan"   action="" class="form-horizontal">
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Kegiatan</div>

    </div>
    <div class="portlet-body">
        <div class="form-horizontal" >
            <div class="form-group">
                <label class="col-md-3 control-label">Kode Kegiatan:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="tahun" id="tahun" value="${tahunAnggaran}" />
                        <input type="hidden" id="idskpd"  value="${skpd.idSkpd}" />
                        
                        <input type="text"  name="kode"  id="kode"    class="form-control " size="30" onkeyup="if (event.keyCode == 13)
                                tampil()" />
                    </div>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Kegiatan:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="text"  name="nama"  id="nama"    class="form-control " size="30" onkeyup="if (event.keyCode == 13)
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
                    <th>Nama</th>
                    <th>Pilih</th>
                </tr>
            </thead>
            <tbody  >
            </tbody>
        </table> 
    </div>    
</div>

</form:form>