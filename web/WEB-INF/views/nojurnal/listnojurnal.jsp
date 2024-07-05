<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/nojurnal/listnojurnal.js"></script>    

<script type="text/javascript">
    $(document).ready(function() {
        tampil();
    });
    
    function tampil() {
        grid();
    }
    function ambilskpd(id) {
        $('#nojurpop', window.parent.document).val($("#noJur" + id).val()).change();
        //$('#namaakunpop', window.parent.document).val($("#nama" + id).val()).change();
        //$('#idbaspop', window.parent.document).val(id).change();
        
        parent.$.fancybox.close();
    }
</script>

<form:form   method="post" commandName="refnojur"  id="refsppup"   class="form-horizontal">
   

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Nomor Jurnal</div>
    </div>
        
    <div class="portlet-body">
        <div class="form-group">

            <div class="col-md-5">
                <div class="input-group">
                    <form:hidden path="pengguna.kodeGrup" id='kodegrup' value="${pengguna.kodeGrup}"  />
                    <form:hidden path="skpd.idSkpd" id='idskpd' value="${skpd.idSkpd}"  />
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
                    <th>Nomor Jurnal</th>
                    <th>Pilih</th>
                </tr>
            </thead>
            <tbody  >
            </tbody>
        </table> 
    </div>    
</div>
                        
</form:form>