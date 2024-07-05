<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SP2D</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bankpfk/indexbankpfk ">Data Bank Utama</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Tambah Data Bank Utama </a></li>
</ul>



<form:form  method="post" commandName="refsp2d"  id="refsp2d"   action="${pageContext.request.contextPath}/sp2dblls/prosessimpan" class="form-horizontal">
    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Tambah Data Bank Utama</div>       
        </div>
        <div class="portlet-body flip-scroll">

            <div class="form-group">
                <label class="col-md-3 control-label">Kode Bank Transfer :</label>
                <div class="col-md-4">
                    <input name="kodebanktf" id="kodebanktf" type="text" maxlength="3"  onKeyPress="return numbersonly(this, event)"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bank Transfer :</label>
                <div class="col-md-4">
                    <input name="namabanktf" id="namabanktf" type="text" size="50" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Kode Bank :</label>
                <div class="col-md-4">
                    <input name="kodebank" id="kodebank" type="text" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bank :</label>
                <div class="col-md-4">
                    <input name="namabank" id="namabank" type="text" size="50"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Kode Bank RTGS :</label>
                <div class="col-md-4">
                    <input name="kodebankrtgs" id="kodebankrtgs" type="text" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bank RTGS :</label>
                <div class="col-md-4">
                    <input name="namabankrtgs" id="namabankrtgs" type="text" size="50"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-md-3 control-label">Kode Aktif :</label>
                <div class="col-md-4">
                    <input type="checkbox"  id="kodeaktif" name="kodeaktif" checked="true" />   
                </div>
            </div>
            
        </div>
    </div>   

    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" >  
            <!--  <button type="button" class="btn blue" onclick='CEK()'>CEK</button> --> 
            <button type="button" class="btn blue" onclick='cekLengkap()'>Simpan</button>
            <a  href="${pageContext.request.contextPath}/bankpfk/indexbankpfk" class="btn blue" >Kembali</a>
        </div>

    </div> 

</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/bankpfk/addbankinduk.js"></script>


