<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    
    function ambilidskpd() {
        $('#idskpdpop', window.parent.document).val($("#idskpdpop").val()).change();
        
        var id = $("#idskpdpop").val();
        window.localStorage.setItem("idskpdval",id);
        
    }
</script>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">JURNAL</a> 
        <span class="icon-angle-right"></span>
    </li>
    
    <li><a href="#">Buku Besar Jurnal SKPD / PPKD (ALL)</a></li>
</ul>


<form:form   method="post" commandName="refbukubesar"  id="refsppup"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Buku Besar Jurnal SKPD / PPKD (ALL)</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>
                
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD :</label>
                <div class="col-md-4 col-md-9">
                    <input path="idskpdpop" id="idskpdpop" type="hidden" onChange="ceklengkap()" value="${skpdpop}" />
                    <input path="skpdpop" id="skpdpop" type="text" size="75" readonly='true' value="${idskpdpop}" class="m-wrap large"  />
                    <form:hidden path="skpd.idSkpd" id='idskpd' value="${skpdpop}"  />
                    <a id="pilihskpd"  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/skpdpopup/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>
                </div>
            </div>  
            
            <div class="form-group">
                <label class="col-md-3 control-label">Akun :</label>
                <div class="col-md-4 col-md-9">
                    <input path="idbaspop" id="idbaspop" type="hidden" onChange="gridbukubesar(),settglposting(this.value)" value="${idbaspop}" />
                    <input path="idakunpop" id="idakunpop" type="hidden" value="${idakunpop}" />
                    <input path="akunketpop" id="akunketpop"  size="75" type="text" readonly='true' value="${akunketpop}"/> 
                    <a id="akunpilih" onclick="ambilidskpd(),setbtnproses()" class="fancybox fancybox.iframe btn dark" caption="This is 1st title"  href="${pageContext.request.contextPath}/akun/listakunbukubesarprovinsi?target='_top'" title="Pilih Kode Akun"  ><i class="icon-zoom-in"></i> Pilih</a>
                                  
                </div>
            </div>
                    
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Posting Awal :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input type="text"  path="tglPostingAwal" id="tglPostingAwal" readonly="true"  size="14" value="" />
                            <errors path="tglPostingAwal" class="error" />
                        </div>
                    </div>
            </div> 
                    
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Posting Akhir :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input type="text"  path="tglPosting" id="tglPosting" class="required date-picker2 entrytanggal valid" size="19" onChange="setbtncetak()" value=""/>
                            <errors path="tglPosting" class="error" />
                        </div>
                    </div>
            </div>         
            
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="btnproses" type="button" class="btn blue" onclick='simpan()' href="#" > Proses Buku Besar </button>
                    <button id="btncetak" type="button" class="btn blue" onclick='cetakjurnal()' href="#" > Cetak </button>
                  <!--  <button type="button" class="btn blue" onclick='cek()' href="#" > cek </button> -->
                </div>
            </div>   
           
        </div>
    </div> 
                    
    <div class="portlet box">
        <form id="formpagusppgup">
            <div class="portlet-title">

            </div>
            <div class="portlet-body">
                <table id="btlspdtable" class="table table-striped table-bordered table-condensed table-hover " >
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>No Urut</th>
                            <th>Tanggal</th>
                            <th>No Jurnal</th> 
                            <th>No Dokumen</th> 
                            <th>Debet</th>
                            <th>Kredit</th>
                            <th>Saldo</th>
                        </tr>
                    </thead>
                    <tbody id="btlspdtablebody" >
                    </tbody>                
                </table>
            </div>
        </form>
    </div>
                    
</form:form>
       
<script  type="text/javascript"  src="${pageContext.request.contextPath}/static/js/aplikasi/bukubesar/bukubesarskpdall.js"></script>  

