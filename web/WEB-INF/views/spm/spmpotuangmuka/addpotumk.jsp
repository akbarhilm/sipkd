<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spmpotayat/addpotumk.js"></script>    

<script type="text/javascript">
    $(document).ready(function() {
        sppval = window.localStorage.getItem("idsppval");
        spmval = window.localStorage.getItem("idspmval");
        kontrakval = window.localStorage.getItem("idkontrakval");
        
        getKodeUmk();
        
    });

    function ambilskpd(id) {
        $('#bebanSpjpop', window.parent.document).val($("#beban" + id).val()).change();
        $('#ketKegpop', window.parent.document).val($("#ket" + id).val()).change();

        parent.$.fancybox.close();
    }
</script>

<form:form   method="post" commandName="refkegiatan"  id="refkegiatan"   action="" class="form-horizontal">
    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Input Potongan UMK</div>
        </div>

        <div class="portlet-body">



            <div class="form-horizontal" >

                <div class="form-group">
                    <label class="col-md-3 control-label">Tahun Anggaran:</label>
                    <div class="col-md-4">
                        <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">SKPD :</label>
                    <div class="col-md-5">
                        <div class="input-group">
                            <input id="idskpd" type="hidden" name="idskpd" value="${skpd.idSkpd}"/>
                            <input id="skpd" type="text"  name="skpd"  id="skpd" readonly="true" class="m-wrap large" size="75"  value="${skpd.kodeSkpd} / ${skpd.namaSkpd}  "  />

                        </div>
                    </div>
                </div>  

            </div>

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9" align="Right">
                    <button type="button" class="btn blue" onclick='simpan()'>Simpan</button>
                    <a href="" id="btnkembali"  class="btn blue" onclick='kembali()' >Kembali</a>
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
                        <th>Kode Akun</th>
                        <th>Nama Akun</th>
                        <th>Pagu UMK</th>
                        <th>Nilai UMK</th>
                        <th>Pot. Sebelum</th>
                        <th>Sisa UMK</th>
                        <th>Nilai Pot</th>
                    </tr>
                </thead>
                <tbody  >
                </tbody>
            </table> 
        </div>    
    </div>
                    
                    
</form:form>