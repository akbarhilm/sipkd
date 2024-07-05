<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/akun/akunlistbukubesarprovinsi.js"></script>    

<script type="text/javascript">
    $(document).ready(function() {
        var id_value = window.localStorage.getItem("idskpdval");
        //console.log("value == "+id_value);
        idskpdval = id_value;
        grid();
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

<form:form   method="post" commandName="refakun"  id="refakun"   action="" class="form-horizontal">
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Akun</div>

    </div>
    
    <div class="portlet-body">
        
        <div class="form-horizontal" >

            <div class="form-group">
                 <br></br>
                <label class="col-md-4 control-label">Kode Akun :</label>
                <div class="col-md-6">
                    <div class="input-group">
                        <input path="idskpdpop" id="idskpdpop" type="hidden" value="${idskpdpop}" />
                        <form:hidden path="tahun" id="tahun" value="${tahunAnggaran}" />
                        
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
                  <!--  <button type="button" class="btn blue" onclick='cek()' href="#" > cek </button>  -->
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

</form:form>