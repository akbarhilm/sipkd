<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/sppbtlgaji/listgaji.js"></script>    

<script type="text/javascript">
    $(document).ready(function() {
        if($('#kodesimpeg', window.parent.document).val() != 0)
            tampil();
        else
            bootbox.alert("Jenis Gaji Harus Dipilih Terlebih Dulu.",function () {
                        parent.$.fancybox.close();
                    });
    });

    function tampil() {
        grid();
    }

    function ambilketerangan(id) {
        //console.log("kode transfer = "+$("#kodetf" + id).val());
        //$('#kodesimpeg', window.parent.document).val($("#kodesimpeg" + id).val()).change();
        $('#idlist', window.parent.document).val($("#idlist" + id).val()).change();
        //$('#bulan', window.parent.document).val($("#bulan" + id).val()).change();
        //$('#ketgaji', window.parent.document).val($("#uraian" + id).val()).change();
        $('#uraian', window.parent.document).val($("#uraian" + id).val()).change();
        $('#jumkotsimpeg', window.parent.document).val(accounting.formatNumber($("#nilaijumkot" + id).val(), 2, '.', ",")).change();
        $('#textjumkotsimpeg', window.parent.document).val(parseFloat($("#nilaijumkot" + id).val())).change();
        

        parent.$.fancybox.close();
    }

</script>

<form:form   method="post" commandName="refkegiatan"  id="refkegiatan"   action="" class="form-horizontal">
    <%--
    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Daftar Bank</div>
        </div>

        <div class="portlet-body">

            <table  width="100%" >

                <tr><td colspan="5"  ></td></tr>


            </table>

            <div class="form-horizontal" >

                <div class="form-group">
                    <label class="col-md-3 control-label">Jenis :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:hidden path="tahun" id="tahun" value="${tahunAnggaran}" />
                            <form:hidden path="skpd.idSkpd" id='idskpd' value="${skpd.idSkpd}"  />

                            <select  id="jenis" name="jenis" onchange="grid(); ">
                                <option value="SEMUA" selected>SEMUA</option> 
                                <option value="RUTIN">RUTIN</option>
                                <option value="SUSULAN">SUSULAN</option>
                                <option value="SELISIH">SELISIH</option>
                                <option value="BELUM ADA">BELUM ADA</option>

                            </select>
                        </div>
                    </div>  
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Macam :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <select  id="macam" name="macam" onchange="grid();">
                                <option value="SEMUA" selected>SEMUA</option> 
                                <option value="GAJI">GAJI</option>
                                <option value="TKD">TKD</option>
                                <option value="TRANSPORT">TRANSPORT</option>
                                <option value="PPH">PPH</option>

                            </select>
                        </div>
                    </div>  
                </div>

            </div>
        </div>
    </div>
                            --%>
    <div class="portlet box">

        <div >
            <table id="banktable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Bulan</th>
                        <th>Publish</th>
                        <th>Penghasilan</th>
                        <th>Jenis</th>
                        <th>Jumlah Kotor</th>
                        <th>Uraian</th>
                        <th>Pilih</th>
                    </tr>
                </thead>
                <tbody  >
                </tbody>
            </table> 
        </div>    
    </div>
</form:form>