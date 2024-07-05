<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SP2D</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bankpfk/indexbankpfk ">Data Bank Cabang</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#"> Data Bank Cabang </a></li>
</ul>

<form:form  method="post" commandName="refsp2d"  id="refsp2d"   action="${pageContext.request.contextPath}/sp2dblls/prosessimpan" class="form-horizontal">
    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Data Bank Cabang</div>       
        </div>
        <div class="portlet-body flip-scroll">

            <div class="form-group">
                <label class="col-md-3 control-label">Kode Bank Utama :</label>
                <div class="col-md-4">
                    <form:hidden path="idBank" id='idBank'  />
                    <form:input path="kodeBankTransfer" type="text" id="kodeBankTransfer" size="5" readonly="true"/>

                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bank Utama :</label>
                <div class="col-md-4">
                    <form:input path="namaBankTransfer" type="text" id="namaBankTransfer" size="40" readonly="true"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bank Cabang :</label>
                <div class="col-md-9">
                    <input type="text" name="ketnama"  id="ketnama"  size="10" /> <input type="text" name="ketnama"  id="namacabang" onkeyup="carinama()" size="40" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Alamat :</label>
                <div class="col-md-4">
                    <TEXTAREA name="alamatcabang" id="alamatcabang" onkeyup="carialamat()" cols="80" ROWS="3" maxlength="250"></TEXTAREA>
                </div>
            </div>
                
            <div class="form-actions fluid">
                <div class="col-md-offset-2 col-md-9" >  
                   <!-- <button type="button" class="btn blue" onclick='grid()'>Cari</button> -->
                   <!-- <button type="button" id="btnUbah" class="btn blue" onclick='cekubah()'>Ubah</button> -->
                   <button type="button" class="btn blue" onclick='cekLengkap()'>Tambah</button>
                    <a  href="${pageContext.request.contextPath}/bankpfk/indexbankpfk" class="btn blue" >Kembali</a>
                </div>

            </div> 

        </div>
    </div>   

    <div class="portlet box">
        <form >
            
            <div class="portlet-body">
                
                <table id="banktable" class="table table-striped table-bordered table-condensed table-hover " >
                    <thead>
                        
                        <tr  >
                            <th>No</th>
                            <th>Nama Bank Cabang</th>
                            <th>Alamat</th>
                            <th>Ubah</th>
                            <th>Hapus</th>
                        </tr>
                    </thead>
                    <tbody id="btlspdtablebody" >
                    </tbody>                
                </table>
            </div>
        </form>
    </div>

</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bankpfk/addbankpfk.js"></script>


