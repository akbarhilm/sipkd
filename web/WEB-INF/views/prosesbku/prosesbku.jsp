<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a> 
        <span class="icon-angle-right"></span>
    </li>

    <li><a href="#">Proses Jurnal BKU</a></li>
</ul>

<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<form:form   method="post" commandName="refneraca"  id="refneraca"   action="${pageContext.request.contextPath}/prosesBku/prosejournal" class="form-horizontal">
    <div onload="" class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Proses Jurnal BKU</div>       
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
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd' value="${skpd.idSkpd}"  />
                        <form:hidden path="skpd.kodeSkpd" id='kodeskpd' value="${skpd.kodeSkpd}"  />
                        <form:hidden path="skpd.namaSkpd" id='namaskpd' value="${skpd.namaSkpd}"  />
                        <form:input path="skpd" type="text"  name="skpd"  id="skpd" readonly="true" class="m-wrap large" size="75"  value="${skpd.kodeSkpd} / ${skpd.namaSkpd}  "  />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="skpd.idSkpd" class="label label-important" />
                    </div>
                </div>
            </div>   

            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" id="tglAwal" name="tglAwal" class="required date-picker2 entrytanggal valid" size="14" value="" onchange="gridbku()"/>
                    </div>
                </div>  
            </div>


            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <!-- <button type="button" id="btnsimpan" class="btn blue" onclick='simpan()' href="#" > Proses Jurnal </button> -->
                    <button id="btnsimpan" type="submit" class="btn blue" onclick="setbutton()"> Proses Jurnal </button>
                    <button id="btncetakxls" type="button" class="btn blue" onclick="cetakjurnalxls()" href="#"> Unduh XLS</button>
                    <!--   <button type="button" class="btn blue" onclick='cek()' href="#" > cek </button> -->
                </div>
            </div> 

            <div class="portlet box">


            </div>

        </div>
    </div>   
                        
    <div class="portlet-body">
                <table id="jourskpdtable" class="table table-striped table-bordered table-condensed table-hover " >
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Tgl Posting</th>
                            <th>Kode Trans</th>
                            <th>Jenis</th>
                            <th>Beban</th>
                            <th>No Bukti</th>
                            <th>Tgl Bukti</th>
                            <th>Kode Keg</th>
                            <th>Nama Kegiatan</th>
                            <th>Kode Akun</th>
                            <th>Nama Akun</th>
                            <th>Keterangan</th>
                            <th>Uang Persediaan</th>
                            <th>Penerima</th> 
                            <th>Pengeluaran</th> 
                            
                        </tr>
                    </thead>
                    
                    <tbody id="jourskpdtablebody" >
                    
                    </tbody>    
                    
                </table>
            </div>
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/prosesbku/prosesbku.js"></script>  

